import java.util.*;

class Customer {
    private final String name;
    private final ArrayList<Rental> rentals = new ArrayList<>();

    public Customer(String newname) {
        name = newname;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        double totalAmount = 0;
        var frequentRenterPoints = 0;
        var stringResultBuilder = new StringBuilder();
        stringResultBuilder.append("Rental Record for ")
                .append(this.getName())
                .append("\n\tTitle\t\tDays\tAmount\n")
        ;

        for (Rental eachRental : rentals){
            double thisAmount;
            thisAmount = amountFor(eachRental);
            frequentRenterPoints++;
            if ((eachRental.getMovie().getPriceCode() == Movie.NEW_RELEASE) && eachRental.getDaysRented() > 1)
                frequentRenterPoints++;
            stringResultBuilder.append("\t")
                    .append(eachRental.getMovie().getTitle())
                    .append("\t\t")
                    .append(eachRental.getDaysRented())
                    .append("\t")
                    .append(thisAmount)
                    .append("\n");
            totalAmount += thisAmount;
        }

        //add footer lines
        stringResultBuilder
                .append("Amount owed is ")
                .append(totalAmount)
                .append("\n").append("You earned ")
                .append(frequentRenterPoints)
                .append(" frequent renter points");
        return stringResultBuilder.toString();
    }

    private double amountFor(Rental each) {
        double thisAmount = 0;
        switch (each.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                thisAmount += 2;
                if (each.getDaysRented() > 2)
                    thisAmount += (each.getDaysRented() - 2) * 1.5;
                break;
            case Movie.NEW_RELEASE:
                thisAmount += each.getDaysRented() * 3;
                break;
            case Movie.CHILDRENS:
                thisAmount += 1.5;
                if (each.getDaysRented() > 3)
                    thisAmount += (each.getDaysRented() - 3) * 1.5;
                break;
        }
        return thisAmount;
    }

}
    