import java.time.LocalDateTime;
import java.time.Month;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("===============================================");
        System.out.println("        БАНКОВСКИЙ СЧЕТ - ДЕМОНСТРАЦИЯ");
        System.out.println("===============================================");
        System.out.println();
        
        System.out.println("1. СОЗДАНИЕ СЧЕТОВ");
        System.out.println("-----------------------------------------------");
        
        BankAccount account1 = new BankAccount("Иван Петров");
        account1.printInfo();
        
        BankAccount account2 = new BankAccount("Мария Смирнова", 10000);
        account2.printInfo();
        
        BankAccount account3 = new BankAccount(
            "Петр Сидоров", 
            5000, 
            LocalDateTime.of(2023, Month.JANUARY, 15, 10, 30)
        );
        account3.printInfo();
        
        System.out.println("\n2. ДЕМОНСТРАЦИЯ ПОЛЕЙ КЛАССА");
        System.out.println("-----------------------------------------------");
        
        System.out.println("Поле ownerName (имя владельца)");
        System.out.println("  Значение: " + account2.getOwnerName());
        System.out.println("  Тип: String");
        System.out.println();
        
        System.out.println("Поле balance (баланс)");
        System.out.println("  Значение: " + account2.getBalance() + " руб.");
        System.out.println("  Тип: int");
        System.out.println();
        
        System.out.println("Поле openingDate (дата открытия)");
        System.out.println("  Значение: " + account2.getOpeningDate());
        System.out.println("  Тип: LocalDateTime");
        System.out.println();
        
        System.out.println("Поле isBlocked (флаг блокировки)");
        System.out.println("  Значение: " + account2.isBlocked());
        System.out.println("  Тип: boolean");
        System.out.println();
        
        System.out.println("Поле number (номер счета)");
        System.out.println("  Значение: " + account2.getNumber());
        System.out.println("  Тип: String");
        System.out.println();
        
        System.out.println("3. ДЕМОНСТРАЦИЯ МЕТОДА equals()");
        System.out.println("-----------------------------------------------");
        
        // Создаем два счета с одинаковыми номерами (для демонстрации)
        BankAccount account4 = new BankAccount("Тестовый Счет");
        BankAccount account5 = new BankAccount("Дубликат Счета");
        
        // Принудительно устанавливаем одинаковые номера
        account4.setNumber("12345678");
        account5.setNumber("12345678");
        
        System.out.println("Счет 1: " + account4.getNumber() + " (" + account4.getOwnerName() + ")");
        System.out.println("Счет 2: " + account5.getNumber() + " (" + account5.getOwnerName() + ")");
        System.out.println();
        
        System.out.println("account4.equals(account5): " + account4.equals(account5));
        System.out.println("account5.equals(account4): " + account5.equals(account4));
        
        System.out.println("\nСравнение с разными номерами:");
        System.out.println("account1.getNumber(): " + account1.getNumber());
        System.out.println("account2.getNumber(): " + account2.getNumber());
        System.out.println("account1.equals(account2): " + account1.equals(account2));
        
        System.out.println("\nСравнение с null:");
        System.out.println("account1.equals(null): " + account1.equals(null));
        
        System.out.println("\nСравнение с самим собой:");
        System.out.println("account1.equals(account1): " + account1.equals(account1));
        
        System.out.println("\n4. ДЕМОНСТРАЦИЯ МЕТОДА hashCode()");
        System.out.println("-----------------------------------------------");
        
        System.out.println("hashCode account4: " + account4.hashCode());
        System.out.println("hashCode account5: " + account5.hashCode());
        System.out.println("hashCode account1: " + account1.hashCode());
        System.out.println("hashCode account2: " + account2.hashCode());
        
        System.out.println("\nЕсли номера одинаковые, hashCode совпадают:");
        System.out.println("account4.hashCode() == account5.hashCode(): " + (account4.hashCode() == account5.hashCode()));
        
        System.out.println("\n5. ДЕМОНСТРАЦИЯ МЕТОДА toString()");
        System.out.println("-----------------------------------------------");
        System.out.println(account2);
        
        System.out.println("\n6. ОПЕРАЦИИ СО СЧЕТОМ");
        System.out.println("-----------------------------------------------");
        
        account2.deposit(5000);
        account2.deposit(-100);
        account2.withdraw(3000);
        account2.withdraw(20000);
        account2.transfer(account3, 2000);
        account2.transfer(account3, 20000);
        
        System.out.println("\n7. БЛОКИРОВКА / РАЗБЛОКИРОВКА");
        System.out.println("-----------------------------------------------");
        
        account2.block();
        account2.deposit(1000);
        account2.transfer(account3, 1000);
        account2.unblock();
        account2.deposit(1000);
        
        System.out.println("\n8. ИТОГОВАЯ ИНФОРМАЦИЯ");
        System.out.println("-----------------------------------------------");
        
        account1.printInfo();
        account2.printInfo();
        account3.printInfo();
        
        System.out.println("\n===============================================");
        System.out.println("Программа завершена");
        System.out.println("===============================================");
    }
}