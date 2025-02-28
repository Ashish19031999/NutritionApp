package com.group_4_trial_1.Nutri_App_user_Trial.service;


import com.group_4_trial_1.Nutri_App_user_Trial.entity.WeightLog;
import com.group_4_trial_1.Nutri_App_user_Trial.exception.WeightLogNotFoundException;
import com.group_4_trial_1.Nutri_App_user_Trial.repository.WeightLogRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class  WeightLogServiceImpl  implements WeightLogServices {
	private static final Logger logger = LogManager.getLogger(WeightLogServiceImpl.class);
    
    private WeightLogRepository weightLogRepository = null;
    @Autowired
    public void weightLogService(WeightLogRepository weightLogRepository) {
        this.weightLogRepository= weightLogRepository;
    }
    
    @Override
    public void removeWeightLog(Long ID) throws WeightLogNotFoundException {
        // TODO Auto-generated method stub

logger.info("removeWeightLog method initiated");

        boolean exists = weightLogRepository.existsById(ID);
if(!exists){
    throw new WeightLogNotFoundException("The id mentioned" + ID + "doesn't exists");
}
weightLogRepository.deleteById(ID);

logger.info("removeWeightLog method executed");


    }
    
    
 


   @Override
    public WeightLog addWeightLog(WeightLog weightLog)throws WeightLogNotFoundException {
        // TODO Auto-generated method stub
logger.info("addWeightLog method executed");
WeightLog wl=weightLogRepository.save(weightLog);

return wl;
        
    }



   @Override
    public List<WeightLog> showAllWeightLog() {
        // TODO Auto-generated method stub
logger.info("showAllWeightLog method initiated");
        return weightLogRepository.findAll().stream().toList();
    }



   @Override
   public WeightLog updateWeightLog(WeightLog weightLog, Long ID) throws WeightLogNotFoundException {
        // TODO Auto-generated method stub
logger.info("updateWeightLog method initiated");
        WeightLog value=weightLogRepository.findById(ID).orElseThrow(()->new WeightLogNotFoundException("WeightLog with id"+ ID + "does not exist."));
       value.setID(weightLog.getID());
      value.setWeight(weightLog.getWeight());
      value.setCreated_At(weightLog.getCreated_At());
      value.setCreated_At(weightLog.getUpdated_At());
       value.setUserId(weightLog.getUserId());
logger.info("updateWeightLog method executed");
       return weightLogRepository.save(value);
     
    }

}





