import java.time.LocalDateTime;
public class BankAccount {
    public String name;
    private int balance = 0;
    private LocalDateTime dateCreate = LocalDateTime.now();
    private boolean isBlock = false;

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }
    BankAccount(String name){
        this.name = name;
    }
    public int getBalance() {
        return balance;
    }
    public void setBalance(int balance) {
        this.balance = balance;
    }
    public boolean isBlocked(){
        return isBlock;
    }
    public boolean deposit(int amount){
        if (amount<1 || isBlock)
            return false;
        this.balance+=amount;
        return true;
    }
    public boolean withdraw(int amount){
        if (amount<1 || amount>balance || isBlock)
            return false;
        this.balance-=amount;
        return true;
    }
    public boolean transfer(BankAccount otherAccount,int amount){
        if (amount<1 || amount>balance || isBlock || otherAccount.isBlocked())
            return false;
        withdraw(amount);
        otherAccount.deposit(amount);
        return true;
    }
}
