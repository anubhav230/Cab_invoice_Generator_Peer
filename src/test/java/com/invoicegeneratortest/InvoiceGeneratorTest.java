package com.invoicegeneratortest;

import com.invoicegenerator.InvoiceGenerator;
import com.invoicegenerator.InvoiceSummary;
import com.invoicegenerator.Ride;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InvoiceGeneratorTest {
      InvoiceGenerator invoiceGenerator;

      @Before
      public void setUp() {
            invoiceGenerator = new InvoiceGenerator();
      }

      @Test
      public void givenDistanceAndTime_ShouldReturnTotalFare() {
            InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
            double distance = 2.0;
            int time = 5;
            double fare = invoiceGenerator.calculateFare(RideType.NORMAL, distance, time);
            Assert.assertEquals(25, fare, 0.0);
      }

      @Test
      public void givenLessDistanceOrTime_ShouldReturnMinFare() {
            InvoiceGenerator invoiceGenerator = new InvoiceGenerator();
            double distance = 0.1;
            int time = 1;
            double fare = invoiceGenerator.calculateFare(RideType.NORMAL, distance, time);
            Assert.assertEquals(5, fare, 0.0);
      }

      @Test
      public void givenMultipleRide_ShouldReturnInvoiceSummary() {
            Ride[] rides = {new Ride(RideType.NORMAL,2.0, 5), new Ride(RideType.NORMAL,0.1, 1),
                    new Ride(RideType.NORMAL,4.0, 25), new Ride(RideType.NORMAL,3.0, 20)};
            InvoiceSummary summary = invoiceGenerator.calculateFare(rides);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4, 145.0);
            Assert.assertEquals(expectedInvoiceSummary, summary);
      }

      @Test
      public void givenUserIdAndRides_ShouldReturnInvoiceSummary() {
            String userId = "Prajyot";
            Ride[] rides = {new Ride(RideType.NORMAL,2.0, 5), new Ride(RideType.NORMAL,0.1, 1),
                    new Ride(RideType.NORMAL,4.0, 25), new Ride(RideType.NORMAL,3.0, 20)};
            invoiceGenerator.addRides(userId, rides);
            InvoiceSummary summary = invoiceGenerator.getInvoiceSummary(userId);
            InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(4, 145.0);
            Assert.assertEquals(expectedInvoiceSummary, summary);
      }
}
