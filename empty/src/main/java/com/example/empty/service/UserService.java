package com.example.empty.service;

import com.example.empty.dao.demo.PersonMapper;
import com.example.empty.model.entity.demo.Person;
import com.example.empty.model.viewmodel.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class UserService {

    @Autowired
    PersonMapper personMapper;

    public Person selectByPrimaryKey(Integer id) {
        try {
            return personMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /*
自动回滚
@Transactional默认只回滚RunTimeException级别，
如果需要回滚到Exception级别才需要指定@Transactional(rollbackFor=Exception.class) ，Exception还要抛出。
表示Exception级别及一下均会回滚
 */
    @Transactional(rollbackFor = Exception.class)
    public MessageResult<Void> insert(Person person) {
        MessageResult<Void> messageResult = new MessageResult<>();
        try {
//                OrderDetail orderDetail=new OrderDetail();
//                orderDetail.setOrderid(order.getId());
//                List<OrderDetail> orderDetailList=orderDetailMapper.select(orderDetail);
            Integer result = personMapper.insert(person);
            if (result <= 0) {
                messageResult.setMessage("删除失败");
                messageResult.setSuccess(false);
                return messageResult;
            }
//            Integer re = orderDetailMapper.deleteByOrderId(order);
//            if (re <= 0) {
//                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
//                messageResult.setMessage("删除失败");
//                messageResult.setSuccess(false);
//                return messageResult;
//            }
            messageResult.setSuccess(true);
        } catch (Exception ex) {
            messageResult.setMessage(ex.getMessage());
            messageResult.setSuccess(false);
            //事务回滚 手动回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

            //或者
//            throw  new  Exception(ex.getMessage());
//            throw ex;
        } finally {
            return messageResult;
        }
    }

}
