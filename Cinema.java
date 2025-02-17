package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        boolean running = true, validInput = true;
        int rowNum = 0, seatNum = 0, tempIncome = 0, tempIncome2 = 0;
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int cols = sc.nextInt();
        System.out.println();
        String[][] cinema = new String[rows+1][cols+1];
        fillArray(cinema);

        while(running) {

            System.out.println("1. Show the seats\n" +
                    "2. Buy a ticket\n" +
                    "3. Statistics\n" +
                    "0. Exit\n");

            switch(sc.nextInt()) {
                case 1:
                    printSeats(cinema);
                    System.out.println();
                break;
                case 2: while(validInput) {
                    System.out.println("Enter a row number:");
                    rowNum = sc.nextInt();
                    System.out.println("Enter a seat number in that row:");
                    seatNum = sc.nextInt();

                    if(rowNum > rows || seatNum > cols) {
                        System.out.println("Wrong input!\n");
                    }else if("B" == cinema[rowNum][seatNum]) {
                        System.out.println("That ticket has already been purchased!\n");
                    }else{
                        validInput = false;
                    }
                }
                    validInput = true;
                    tempIncome = ticketPrice(rowNum,rows,cols);
                    tempIncome2 += tempIncome;
                    seatPurchaseRequest(rowNum,seatNum,rows,cols,cinema);
                break;
                case 3: cinemaStats(cinema,rows,cols,rowNum,tempIncome2);
                break;
                case 0: running = false;
                break;
                default:
                break;
            }
        }

    }

    public static void printSeats(String[][] cinema){
        System.out.println("Cinema:");

        for(int i=0;i<cinema.length;i++){
            for(int j=0;j<cinema[i].length;j++){
                System.out.print(cinema[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void fillArray(String[][] cinema){
        int count = 1;

        for(int i=0;i<cinema.length;i++){
            for(int j=0;j<cinema[i].length;j++){
                cinema[i][j] = ("S");
            }
        }

        for(int i=0;i<1;i++){
            for(int j=1;j<cinema[i].length;j++){
                cinema[0][j] = String.valueOf(count);
                count++;
            }
        }

        count = 1;

        for(int i=1;i<cinema.length;i++){
            for(int j=0;j<2;j++){
                cinema[i][0] = String.valueOf(count);
            }
            count++;
        }

        cinema[0][0] = (" ");

    }

    public static void seatPurchaseRequest(int rowNum, int seatNum, int rows, int cols, String[][] cinema) {
        cinema[rowNum][seatNum] = "B";
        System.out.println();
        System.out.println("Ticket price: $" + ticketPrice(rowNum,rows,cols) + "\n");
    }

    public static int ticketPrice(int rowNum, int rows, int cols) {
        int totalSeats = rows*cols;
        int ticketPrice = 0;

        if(totalSeats < 60){
            ticketPrice = 10;
        } else if(rows%2==0) {
            if(rowNum<=rows/2){
                ticketPrice = 10;
            }else{
                ticketPrice = 8;
            }
        }else if(rows%2==1){
            if(rowNum<=rows/2){
                ticketPrice = 10;
            }else{
                ticketPrice = 8;
            }
        }

        return ticketPrice;
    }

    public static int totalPrice(int rows, int cols) {
        int totalSeats = rows*cols;
        int fullCinema = 0;
        int tempProfit = rows/2;

            if(totalSeats < 60){
                fullCinema = 10*totalSeats;
            } else if(rows%2==0) {
                fullCinema = tempProfit*cols*10 + tempProfit*cols*8;
            }else if(rows%2==1){
                fullCinema = tempProfit*cols*10 + (tempProfit+1)*cols*8;
            }

        return fullCinema;
    }

    public static void cinemaStats(String[][] cinema, int rows, int cols, int rowNum, int tempIncome2){
        int totalSeats = rows*cols;
        int seatsSold = 0;
        double percentageSold = 0;
        int currentIncome = tempIncome2;
        int totIncome = totalPrice(rows,cols);

        String statistics = "Number of purchased tickets: %d\n" +
                "Percentage: %.2f%%\n" +
                "Current income: $%d\n" +
                "Total income: $%d\n";

        for(int i=0;i<cinema.length;i++){
            for(int j=0;j<cinema[i].length;j++){
                if("B" == cinema[i][j]){
                    seatsSold++;
                    percentageSold = seatsSold;
                    percentageSold /= rows*cols;
                    percentageSold *= 100;
                }
            }
        }

        String statisticsResult = String.format(statistics,seatsSold,percentageSold,currentIncome,totIncome);
        System.out.println(statisticsResult);

    }
}