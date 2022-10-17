
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        YearlyReport yearlyReport = new YearlyReport("resources/y.2021.csv");

        MonthlyReport[] monthlyReport = new MonthlyReport[3];

        for (int i = 0; i < monthlyReport.length; i++) {
            monthlyReport[i] = new MonthlyReport("resources/m.20210" + (i + 1) + ".csv");
        }

        while (true) {
            printMenu();
            int command = scanner.nextInt();
            if (command == 1) {
                for (int i = 0; i < monthlyReport.length; i++) {
                    if (!monthlyReport[i].data.isEmpty()) {
                        System.out.println("Месячный отчет " + (i + 1) + " считан");
                    }
                }
            } else if (command == 2) {
                if (!yearlyReport.data.isEmpty()) {
                    System.out.println("Годовой отчет считан.");
                }
            } else if (command == 3) {
                for (int i = 0; i < monthlyReport.length; i++) {
                    if (!monthlyReport[i].data.isEmpty() && !yearlyReport.data.isEmpty()) {
                        if (monthlyReport[i].sumExpenses() == yearlyReport.sumExpensesMonth(i)) {
                            System.out.println("Расходы по месяцу " + (i + 1) + " совпадают. Значение составляет:");
                            System.out.println(monthlyReport[i].sumExpenses());
                        } else {
                            System.out.println("Расходы по месяцу " + (i + 1) + " НЕ совпадают!!!");
                        }

                        if (monthlyReport[i].sumProfit() == yearlyReport.sumProfitMonth(i)) {
                            System.out.println("Доходы по месяцу " + (i + 1) + " совпадают. Значение составляет:");
                            System.out.println(monthlyReport[i].sumProfit());
                            System.out.println("******************");
                        } else {
                            System.out.println("Доходы по месяцу " + (i + 1) + " НЕ совпадают!!!");
                            System.out.println("******************");
                        }
                    } else {
                        System.out.println("Отчет НЕ считан, выполнить сверку не возможно.");
                        break;
                    }
                }
            } else if (command == 4) {
                for (int i = 0; i < monthlyReport.length; i++) {
                    System.out.println("Статистика за месяц " + (i + 1));
                    monthlyReport[i].printMonthlyReport();
                    System.out.println("*******************");
                }
            } else if (command == 5) {
                yearlyReport.printYearlyReport();
                System.out.println("******************");
            } else if (command == 999) {
                break;
            } else {
                System.out.println("Извините, такой команды пока нет.");
            }
        }
    }

    public static void printMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Считать все месячные отчёты");
        System.out.println("2. Считать годовой отчёт");
        System.out.println("3. Сверить отчёты");
        System.out.println("4. Вывести информацию о всех месячных отчётах");
        System.out.println("5. Вывести информацию о годовом отчёте");
        System.out.println("999. Выход");
    }
}

