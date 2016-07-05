package cc.study.springmvc.dao;

import cc.study.springmvc.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2016/5/23.
 */
@Repository
public class LoginLogDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    public void insertLoginLog(LoginLog loginLog)
    {
        String sqlStr="insert into t_login_log(user_id,ip,login_datetime) values(?,?,?)";
        jdbcTemplate.update(sqlStr,new Object[]{loginLog.getUserId(),loginLog.getIp(),loginLog.getLoginDate()});
    }
}
