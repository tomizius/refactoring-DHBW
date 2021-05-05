import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TestingRefactoring {
    @Test
    void testingMovie(){
        var m = new Movie("Movie1", 3);
        Assertions.assertEquals("Movie1", m.getTitle());
        Assertions.assertEquals(3, m.getPriceCode());
    }

    @Test
    void testingRental(){
        var m = new Movie("Movie2", 1);
        var r = new Rental(m, 25);
        Assertions.assertEquals(25, r.getDaysRented());
        Assertions.assertEquals("Movie2", r.getMovie().getTitle());
        Assertions.assertEquals(1, r.getMovie().getPriceCode());
    }

    @Test
    void testingCustomer(){
        var m1 = new Movie("movie1", 1);
        var m2 = new Movie("movie2", 2);
        var r1 = new Rental(m1, 10);
        var r2 = new Rental(m2, 5);
        var c1 = new Customer("John");
        c1.addRental(r1);   c1.addRental(r2);
        Assertions.assertTrue(c1.statement().contains("for John"));
        Assertions.assertTrue(c1.statement().contains("movie1\t\t10\t30.0"));
        Assertions.assertTrue(c1.statement().contains("movie2\t\t5\t4.5"));
        Assertions.assertTrue(c1.statement().contains("Amount owed is 34.5"));
        Assertions.assertTrue(c1.statement().contains("You earned 3 frequent renter points"));
    }

}
