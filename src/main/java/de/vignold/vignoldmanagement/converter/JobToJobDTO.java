package de.vignold.vignoldmanagement.converter;

import de.vignold.vignoldmanagement.dto.JobDTO;
import de.vignold.vignoldmanagement.entity.Job;

import java.util.ArrayList;
import java.util.List;

public class JobToJobDTO {

    public static List<JobDTO> convertJobTOJobDTO(List<Job> jobList) {
        List<JobDTO> jobDTOsList = new ArrayList<>();
        for (Job job : jobList) {
            JobDTO jobDTO = new JobDTO();
            jobDTO.setDescription(job.getDescription());
            jobDTO.setCreatedDate(job.getCreatedDate());
            jobDTO.setUpdatedDate(job.getUpdatedDate());
            jobDTOsList.add(jobDTO);
        }
        return jobDTOsList;
    }
}
