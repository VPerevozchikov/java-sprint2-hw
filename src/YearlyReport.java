import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class YearlyReport {
    public HashMap<Integer, YearlyStat> data = new HashMap<>();
    int numberOfMonth = 3;

    public YearlyReport(String path) {
        String content = readFileContentsOrNull(path);
        String[] lines = content.split("\r?\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");
            int month = Integer.parseInt(parts[0]);
            int amount = Integer.parseInt(parts[1]);
            boolean isExpense = Boolean.parseBoolean(parts[2]);
            if (!data.containsKey(month)) {
                data.put(month, new YearlyStat());
            }
            YearlyStat stat = data.get(month);
            if (isExpense) {
                stat.expenses += amount;
            } else {
                stat.profit += amount;
            }
        }
    }

    public void printYearlyReport() {
        System.out.println("За 2021 год");
        for (Integer numMonth : data.keySet()) {
            System.out.println("За месяц № " + numMonth);
            YearlyStat stat = data.get(numMonth);
            System.out.println("Прибыль " + (stat.profit - stat.expenses));
        }

        System.out.println("Средний расход за все месяцы в году:");
        sumAllExpenses();

        System.out.println("Средний доход за все месяцы в году:");
        sumAllProfit();

    }

    public void sumAllProfit(){
        int sumAllProfit = 0;
        for (Integer numMonth : data.keySet()) {
            YearlyStat stat = data.get(numMonth);
            sumAllProfit += stat.profit;
        }
        System.out.println(sumAllProfit/numberOfMonth);
    }

    public void sumAllExpenses(){
        int sumAllExpenses = 0;
        for (Integer numMonth : data.keySet()) {
            YearlyStat stat = data.get(numMonth);
            sumAllExpenses += stat.expenses;
        }
        System.out.println(sumAllExpenses/numberOfMonth);
    }

    public int sumExpensesMonth (int i) {
        YearlyStat stat = data.get(i + 1);
        return stat.expenses;
    }
    public int sumProfitMonth (int i) {
        YearlyStat stat = data.get(i + 1);
        return stat.profit;
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
