package de.vignold.vignoldmanagement.entity;

import de.vignold.vignoldmanagement.entity.state.State;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.time.ZonedDateTime;

@Entity
@Transactional
@Table(
        name = "product",
        uniqueConstraints = @UniqueConstraint(name = "uc_productid", columnNames = {"id"})
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "job_id")
    private Job job;

    @Column(nullable = false)
    @NotNull(message = "Job description can not be null!")
    private String description;

    @Column(nullable = false)
    private Boolean deleted = false;

    @Column(nullable = false)
    @NotNull(message = "State can not be null!")
    private State state;

    @Column(name = "created_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime createdDate = ZonedDateTime.now();

    @Column(name = "updated_date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime updatedDate = ZonedDateTime.now();

    @Column(name = "operator_id")
    @NotNull(message = "Operator ID can not be null or empty!")
    private int operatorId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public ZonedDateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(ZonedDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(int operatorId) {
        this.operatorId = operatorId;
    }
}
