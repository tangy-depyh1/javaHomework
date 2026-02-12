public class Main {
    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount("Аккаунт 1");
        BankAccount acc2 = new BankAccount("Аккаунт 2");

        acc1.setBalance(1000);
        acc2.setBalance(500);

        System.out.println("Баланс до: acc1=" + acc1.getBalance() + ", acc2=" + acc2.getBalance());
        boolean result = acc1.transfer(acc2, 200);
        System.out.println("Перевод выполнен: " + result);
        System.out.println("Баланс после: acc1=" + acc1.getBalance() + ", acc2=" + acc2.getBalance());

        result = acc1.transfer(acc1, 100);
        System.out.println("Перевод выполнен: " + result);

        result = acc1.transfer(acc2, 5000);
        System.out.println("Перевод выполнен: " + result);

        System.out.println("Они равны:"+acc1.equals(acc2));
        System.out.println("Они равны:"+acc1.equals(acc1));
    }
}