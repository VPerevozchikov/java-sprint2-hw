import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class MonthlyReport {
    public HashMap<String, MonthlyStat> data = new HashMap<>();

    public MonthlyReport(String path) {
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int sum_of_one = Integer.parseInt(parts[3]);
            if (!data.containsKey(itemName)) {
                data.put(itemName, new MonthlyStat());
            }
            MonthlyStat stat = data.get(itemName);
            if (isExpense) {
                stat.expenses = quantity * sum_of_one;
            } else {
                stat.profit = quantity * sum_of_one;
            }
        }
    }

    public void printMonthlyReport() {

        System.out.println("Самый прибыльный товар: ");
        calcMaxProfit();

        System.out.println("Самая большая трата: ");
        calcMaxExpense();
    }
    public void calcMaxProfit() {
        int maxProfit = 0;
        String nameMaxProfit = "";
        for (String itemName : data.keySet()) {
            MonthlyStat stat = data.get(itemName);
            if (maxProfit < stat.profit) {
                maxProfit = stat.profit;
                nameMaxProfit = itemName;
            }
        }
        System.out.println(nameMaxProfit + " " + maxProfit);
    }

    public void calcMaxExpense() {
        int maxExpenses = 0;
        String nameMaxExpenses = "";
        for (String itemName : data.keySet()) {
            MonthlyStat stat = data.get(itemName);
            if (maxExpenses < stat.expenses) {
                maxExpenses = stat.expenses;
                nameMaxExpenses = itemName;
            }
        }
        System.out.println(nameMaxExpenses + " " + maxExpenses);
    }

    public int sumExpenses() {
        int sumExpenses = 0;
        for (String itemName : data.keySet()) {
            MonthlyStat stat = data.get(itemName);
            sumExpenses += stat.expenses;
        }
        return sumExpenses;
    }

    public int sumProfit() {
        int sumProfit = 0;
        for (String itemName : data.keySet()) {
            MonthlyStat stat = data.get(itemName);
            sumProfit += stat.profit;
        }
        return sumProfit;
    }

    private String readFileContentsOrNull(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно, файл не находится в нужной директории.");
            return null;
        }
    }
}
