package com.neusoft.elmboot.po;

import java.math.BigDecimal;

public class VirtualWallet {
  private String userid;
//  private long createTime = System.currentTimeMillis();
  private BigDecimal balance = BigDecimal.ZERO;

public String getId() {
    return userid;
}
public void setId(String userid) {
    this.userid = userid;
}
//public long getCreateTime() {
//    return createTime;
//}
//public void setCreateTime(long createTime) {
//    this.createTime = createTime;
//}
public BigDecimal getBalance() {
    return balance;
}
public void setBalance(BigDecimal balance) {
    this.balance = balance;
}

    public void debit(BigDecimal amount) throws Exception {
        if (this.balance.compareTo(amount) < 0) {
            throw new Exception("余额不足");
        }
        this.balance = this.balance.subtract(amount);
    }

    public void credit(BigDecimal amount) throws Exception {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("存款值不合规");
        }
        this.balance = this.balance.add(amount);
    }


}
