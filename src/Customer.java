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
        Enumeration<Rental> enumRentals = Collections.enumeration(rentals);
        var stringResultBuilder = new StringBuilder();
        stringResultBuilder.append("Rental Record for ")
                .append(this.getName())
                .append("\n")
                .append("\t" + "Title" + "\t" + "\t" + "Days" + "\t" + "Amount" + "\n")
        ;

        while (enumRentals.hasMoreElements()) {
            double thisAmount;
            var each = enumRentals.nextElement();
            //determine amounts for each line
            thisAmount = amountFor(each);
            // add frequent renter points
            frequentRenterPoints++;
            // add bonus for a two day new release rental
            if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1)
                frequentRenterPoints++;
            //show figures for this rental
            stringResultBuilder.append("\t")
                    .append(each.getMovie().getTitle())
                    .append("\t")
                    .append("\t")
                    .append(each.getDaysRented())
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
    