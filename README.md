# supermarket-pricing

Calculates the total price for a shopping cart at a supermarket. 

Some items available are based on price per item, some items are based on weight. There are also group discounts to be aware of:

* Three cans of beans for the price of two.
* Two cans of Coke, for £1.

A receipt would look like this:

```

Beans                0.50
Beans                0.50
Beans                0.50
Coke                 0.70
Coke                 0.70
Oranges
0.200 kg @  £1.99/kg 0.40
                    -----
Sub-total            3.30

Savings
Beans 3 for 2       -0.50
Coke 2 for £1       -0.40
                    -----
Total savings       -0.90
-------------------------
Total to Pay         2.40

```

## Assumptions

* Minimum price for one item is 0.01.
* Based on the problem, I'm guessing there's no need to provide an interface to run the application and that it will be executed only through the tests.

## Installation

Simply clone this repo in your local machine.

## Run tests

From the root folder, run:

`mvn test`

to execute all the test cases.

