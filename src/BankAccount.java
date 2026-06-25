import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Objects;

public class BankAccount {
    
    // Поля класса
    private String ownerName;         // имя владельца
    private int balance;               // баланс
    private LocalDateTime openingDate; // дата открытия
    private boolean isBlocked;         // флаг блокировки
    private String number;             // номер счета (8 цифр)
    
    // ============ КОНСТРУКТОРЫ ============
    
    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0;
        this.openingDate = LocalDateTime.now();
        this.isBlocked = false;
        this.number = generateAccountNumber();
    }
    
    public BankAccount(String ownerName, int initialBalance) {
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.openingDate = LocalDateTime.now();
        this.isBlocked = false;
        this.number = generateAccountNumber();
    }
    
    public BankAccount(String ownerName, int initialBalance, LocalDateTime openingDate) {
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.openingDate = openingDate;
        this.isBlocked = false;
        this.number = generateAccountNumber();
    }
    
    // ============ ГЕТТЕРЫ И СЕТТЕРЫ ============
    
    public String getOwnerName() {
        return ownerName;
    }
    
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    
    public int getBalance() {
        return balance;
    }
    
    public void setBalance(int balance) {
        this.balance = balance;
    }
    
    public LocalDateTime getOpeningDate() {
        return openingDate;
    }
    
    public void setOpeningDate(LocalDateTime openingDate) {
        this.openingDate = openingDate;
    }
    
    public boolean isBlocked() {
        return isBlocked;
    }
    
    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    // ============ МЕТОД ГЕНЕРАЦИИ НОМЕРА ============
    
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10)); // цифра от 0 до 9
        }
        return sb.toString();
    }
    
    // ============ МЕТОДЫ ============
    
    public boolean deposit(int amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: сумма пополнения должна быть положительной");
            return false;
        }
        
        if (isBlocked) {
            System.out.println("Ошибка: счет заблокирован");
            return false;
        }
        
        balance += amount;
        System.out.println("Пополнение успешно. Сумма: " + amount + ". Новый баланс: " + balance);
        return true;
    }
    
    public boolean withdraw(int amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: сумма снятия должна быть положительной");
            return false;
        }
        
        if (isBlocked) {
            System.out.println("Ошибка: счет заблокирован");
            return false;
        }
        
        if (amount > balance) {
            System.out.println("Ошибка: недостаточно средств. Баланс: " + balance + ", запрошено: " + amount);
            return false;
        }
        
        balance -= amount;
        System.out.println("Снятие успешно. Сумма: " + amount + ". Новый баланс: " + balance);
        return true;
    }
    
    public boolean transfer(BankAccount otherAccount, int amount) {
        if (otherAccount == null) {
            System.out.println("Ошибка: счет получателя не существует");
            return false;
        }
        
        if (this == otherAccount) {
            System.out.println("Ошибка: нельзя перевести деньги самому себе");
            return false;
        }
        
        if (amount <= 0) {
            System.out.println("Ошибка: сумма перевода должна быть положительной");
            return false;
        }
        
        if (this.isBlocked) {
            System.out.println("Ошибка: ваш счет заблокирован");
            return false;
        }
        
        if (otherAccount.isBlocked) {
            System.out.println("Ошибка: счет получателя заблокирован");
            return false;
        }
        
        if (amount > this.balance) {
            System.out.println("Ошибка: недостаточно средств. Баланс: " + this.balance + ", запрошено: " + amount);
            return false;
        }
        
        this.balance -= amount;
        otherAccount.balance += amount;
        
        System.out.println("Перевод успешен. Сумма: " + amount + " переведена с " + this.ownerName + " на " + otherAccount.ownerName);
        return true;
    }
    
    public void block() {
        this.isBlocked = true;
        System.out.println("Счет " + ownerName + " заблокирован");
    }
    
    public void unblock() {
        this.isBlocked = false;
        System.out.println("Счет " + ownerName + " разблокирован");
    }
    
    public void printInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedDate = openingDate.format(formatter);
        
        String status = isBlocked ? "ЗАБЛОКИРОВАН" : "АКТИВЕН";
        
        System.out.println("\n===============================================");
        System.out.println("        ИНФОРМАЦИЯ О СЧЕТЕ");
        System.out.println("===============================================");
        System.out.println("Номер:     " + number);
        System.out.println("Владелец:  " + ownerName);
        System.out.println("Баланс:    " + balance + " руб.");
        System.out.println("Открыт:    " + formattedDate);
        System.out.println("Статус:    " + status);
        System.out.println("===============================================");
    }
    
    // ============ ПЕРЕОПРЕДЕЛЕНИЕ toString ============
    
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        String formattedDate = openingDate.format(formatter);
        String status = isBlocked ? "ЗАБЛОКИРОВАН" : "АКТИВЕН";
        
        return "BankAccount{" +
               "номер='" + number + '\'' +
               ", владелец='" + ownerName + '\'' +
               ", баланс=" + balance + " руб." +
               ", дата открытия=" + formattedDate +
               ", статус=" + status +
               '}';
    }
    
    // ============ ПЕРЕОПРЕДЕЛЕНИЕ equals И hashCode ============
    
    /**
     * Сравнивает два счета по номеру
     * @param obj объект для сравнения
     * @return true если счета имеют одинаковый номер
     */
    @Override
    public boolean equals(Object obj) {
        // 1. Проверка: если ссылки указывают на один объект
        if (this == obj) {
            return true;
        }
        
        // 2. Проверка: если объект null
        if (obj == null) {
            return false;
        }
        
        // 3. Проверка: если классы разные
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        // 4. Приведение типа
        BankAccount other = (BankAccount) obj;
        
        // 5. Сравнение по номеру счета
        return Objects.equals(number, other.number);
    }
    
    /**
     * Возвращает хеш-код объекта
     * @return хеш-код на основе номера счета
     */
    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}