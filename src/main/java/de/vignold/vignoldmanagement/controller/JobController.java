package de.vignold.vignoldmanagement.controller;

import de.vignold.vignoldmanagement.converter.JobToJobDTO;
import de.vignold.vignoldmanagement.dao.JobRepository;
import de.vignold.vignoldmanagement.dao.ProductRepository;
import de.vignold.vignoldmanagement.dto.JobDTO;
import de.vignold.vignoldmanagement.entity.Job;
import de.vignold.vignoldmanagement.entity.Product;
import de.vignold.vignoldmanagement.entity.state.State;
import de.vignold.vignoldmanagement.exception.ProductAlreadyInProgressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/all")
    public ResponseEntity<List<JobDTO>> findAll() {
        List<JobDTO> jobDTOS = new ArrayList<>();
        List<Job> jobList = jobRepository.findAll();
        if (!jobList.isEmpty()) {
            return ResponseEntity.ok(JobToJobDTO.convertJobTOJobDTO(jobList));
        }
        return null;
    }

    @PostMapping("/create")
    public void createJob(JobDTO jobDTO) {
        Job job = new Job();
        job.setState(State.RUNNING);
        job.setDescription(jobDTO.getDescription());
        job.setCreatedDate(ZonedDateTime.now());
        job.setUpdatedDate(ZonedDateTime.now());
        job.setProduct(new ArrayList<>());
        jobRepository.save(job);
    }

    @DeleteMapping("/delete")
    public void deleteJob(Long id) {
        Job job = jobRepository.getOne(id);
        job.setDeleted(Boolean.TRUE);
        job.setState(State.FINISHED);
        jobRepository.save(job);
    }

    @PutMapping("/update")
    public void updateJob(@RequestParam Long jobId, JobDTO jobDTO) {
        Job job = jobRepository.getOne(jobId);
        job.setDescription(jobDTO.getDescription());
        job.setUpdatedDate(ZonedDateTime.now());
        jobRepository.save(job);
    }

    @PutMapping("/relate")
    @Transactional
    public ResponseEntity<String> relateJobWithProduct(@RequestParam Long jobId, @RequestParam Long productId) {
        Job jobToRelate = jobRepository.findById(jobId).get();
        Product product = productRepository.findById(productId).get();
        Long countOfJobsWithProduct = jobRepository.countByProduct_idAndState(productId, State.RUNNING);
        if (countOfJobsWithProduct == 0L) {
            jobToRelate.getProduct().add(product);
            product.setJob(jobToRelate);
        } else {
            throw new ProductAlreadyInProgressException();
        }
        productRepository.save(product);
        jobRepository.save(jobToRelate);

        return ResponseEntity.ok("Product related successfully!");
    }
}
