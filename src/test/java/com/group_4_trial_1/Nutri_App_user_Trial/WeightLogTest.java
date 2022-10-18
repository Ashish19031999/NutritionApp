
package com.group_4_trial_1.Nutri_App_user_Trial;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;



import com.group_4_trial_1.Nutri_App_user_Trial.entity.WeightLog;
//import com.sprint_1.payment_module.entity.Payment;
import com.group_4_trial_1.Nutri_App_user_Trial.exception.WeightLogNotFoundException;
import com.group_4_trial_1.Nutri_App_user_Trial.repository.WeightLogRepository;
import com.group_4_trial_1.Nutri_App_user_Trial.service.WeightLogServiceImpl;



@RunWith(SpringRunner.class)
@SpringBootTest
public class WeightLogTest {
    
    @Autowired
    private WeightLogServiceImpl weightLogService;
    
    @MockBean
    private WeightLogRepository weightLogRepository;
    WeightLog w1;
    @BeforeEach
    public void init() {
    w1 = new WeightLog(1,20f,LocalDate.now(),LocalDate.now() ,"ABCD1");
    }


   /*@Test
    void contextLoads() {
    }*/
    
    @Test
    public void showAllWeightLogTest() throws WeightLogNotFoundException {
        when(weightLogRepository.findAll()).thenReturn(Stream.of(new WeightLog(1L,0f,LocalDate.now(),LocalDate.now(),"null"), new WeightLog(2L,0f,LocalDate.now(),LocalDate.now(),"null")).collect(Collectors.toList()));
        assertEquals(2,weightLogService.showAllWeightLog().size());
    }
    
    
   /* public void payTest() throws PaymentNotFoundException{
        PaymentDTO tester= new PaymentDTO(1L,0f,0f,LocalDate.now(),LocalDate.now(),"null",0L);
        float price=0;
        paymentService.pay(tester,price);
        verify(paymentService,times(1)).pay(tester,price);
    
    } */
    @Test
    public void removeWeightLogTest() throws WeightLogNotFoundException {

        when(weightLogRepository.existsById(w1.getID())).thenReturn(true);

        when(weightLogRepository.findById(w1.getID())).thenReturn(Optional.ofNullable(w1));

        weightLogService.removeWeightLog(w1.getID());

        verify(weightLogRepository).delete(w1);
    }
    
    
    /*
    public void addWeightLog() throws WeightLogNotFoundException {
    	
        PaymentDTO tester= new PaymentDTO(1L,0f,0f,LocalDate.now(),LocalDate.now(),"null",0L);
        float discount=0;
        paymentService.addOffer(tester, discount);
        verify(paymentService,times(1)).pay(tester, discount);
        
        
    }
    */
    @Test
    public void addWeightLogTest() throws WeightLogNotFoundException {

        when(weightLogRepository.save(w1)).thenReturn(w1);

        assertEquals(w1.getID(),weightLogService.addWeightLog(w1).getID());
        
       // assertEquals(w1.getWeight(),weightLogService.addWeightLog(w1).getWeight());
        
        assertEquals(w1.getCreated_At(),weightLogService.addWeightLog(w1).getCreated_At());
        
        assertEquals(w1.getUpdated_At(),weightLogService.addWeightLog(w1).getUpdated_At());
        
        assertEquals(w1.getUserId(),weightLogService.addWeightLog(w1).getUserId());

        
    } 
    
    
   // @Test
   /* public void updateWeightLogTest() throws WeightLogNotFoundException {
       // WeightLog tester=  WeightLog(w1);
        when(weightLogService.updateWeightLog(w1,w1.getID())).thenReturn(w1);
        assertEquals(w1,weightLogService.updateWeightLog(w1,w1.getID()));
        
    } */
   @Test
    public void updateWeightLogTest()  throws WeightLogNotFoundException {

        when(weightLogRepository.existsById(w1.getID())).thenReturn(true);

        when(weightLogRepository.findById(w1.getID())).thenReturn(Optional.ofNullable(w1));

        when(weightLogRepository.save(w1)).thenReturn(w1);

      //  assertEquals(w1.getWeight(),weightLogService.updateWeightLog(w1, w1.getID()).getWeight());

        assertEquals(w1.getCreated_At(),weightLogService.updateWeightLog(w1, w1.getID()).getCreated_At());

        assertEquals(w1.getUpdated_At(),weightLogService.updateWeightLog(w1, w1.getID()).getUpdated_At());
        
        assertEquals(w1.getUserId(),weightLogService.updateWeightLog(w1, w1.getID()).getUserId());

    }
    
    
    



}
