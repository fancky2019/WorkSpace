package com.fancky.multipledatasource.service;

import com.fancky.multipledatasource.dao.demo.UserMapper;
import com.fancky.multipledatasource.dao.test.JobMapper;
import com.fancky.multipledatasource.model.entity.demo.User;
import com.fancky.multipledatasource.model.entity.test.Job;
import com.fancky.multipledatasource.model.viewmodel.MessageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

@Service
public class MultipleDataSourceService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    JobMapper jobMapper;

    /*
    spring 必须指定一个库作为事务，无法做到多库事务。
    只有标记了@Primary的主数据源事务会生效，子数据源事务不生效，即使写明名称事务也不生效。

     atomikos 可以做到多库事务。
     atomikos 多数据源事务管理底层事务操作还是和spring一样。
     atomikos 执行情况可以参见transaction-logs目录下的log


    自动回滚：  在@Transactional注解中如果不配置rollbackFor属性,那么事物只会在遇到RuntimeException的时候才会回滚,
    加上rollbackFor=Exception.class,Exception还要抛出。 可以让事物在遇到非运行时异常时也回滚

    手动回滚：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
   */
    @Transactional
    //    @Transactional(rollbackFor = Exception.class)
    public MessageResult<Void> insertAll(User user, Job job) {
        MessageResult<Void> messageResult = new MessageResult<>();
        try {
            Integer re = userMapper.insert(user);
            if (re <= 0) {
                messageResult.setMessage("保存失败");
                messageResult.setSuccess(false);
                return messageResult;
            }

            Integer result = jobMapper.insert(job);
            if (result <= 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                messageResult.setMessage("保存失败");
                messageResult.setSuccess(false);
                return messageResult;
            }
            messageResult.setSuccess(true);
            //引发异常 :两个数据库都回滚
//            Integer m = Integer.valueOf("m");
        } catch (Exception ex) {
            messageResult.setMessage(ex.getMessage());
            messageResult.setSuccess(false);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        } finally {
            return messageResult;
        }
    }
}
