package com.group_4_trial_1.Nutri_App_user_Trial.repository;
import com.group_4_trial_1.Nutri_App_user_Trial.entity.WeightLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WeightLogRepository
	        extends JpaRepository<WeightLog,Long> {

	}


