import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Objects;
public class BankAccount {
    public String name;
    private int balance = 0;
    private LocalDateTime dateCreate = LocalDateTime.now();
    private boolean isBlock = false;
    private String number;

    public LocalDateTime getDateCreate() {
        return dateCreate;
    }

    private void CrateNumber(){
        int num;
        Random rnd = new Random();
        num = rnd.nextInt(100000000);
        this.number = String.format("%08d", num);
    }

    public String getNumber(){
        return number;
    }

    BankAccount(String name) {
        this.name = name;
        CrateNumber();
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public boolean isBlocked() {
        return isBlock;
    }

    public boolean deposit(int amount) {
        if (amount < 1 || isBlock)
            return false;
        this.balance += amount;
        return true;
    }

    public boolean withdraw(int amount) {
        if (amount < 1 || amount > balance || isBlock)
            return false;
        this.balance -= amount;
        return true;
    }

    public boolean transfer(BankAccount otherAccount, int amount) {
        if (amount < 1 || amount > balance || isBlock || otherAccount.isBlocked())
            return false;
        withdraw(amount);
        otherAccount.deposit(amount);
        return true;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String dateStr = dateCreate.format(formatter);
        String status = isBlock ? "Заблокан" : "Активен";
        return String.format(
                "Банковский счет:%n" +
                        "  Имя: %s %n" +
                        "  Номер: %s %n" +
                        "  Баланс: %d руб. %n" +
                        "  Дата создания: %s %n" +
                        "  Статус акка: %s",
                name, number, balance, dateStr, status
        );
    }

    //Решил сравнивать только номер счета
    @Override
    public boolean equals(Object obj) {
        BankAccount other = (BankAccount) obj;
        return Objects.equals(this.number, other.number);
    }

    public int hashCode() {
        return Objects.hash(number);
    }

}