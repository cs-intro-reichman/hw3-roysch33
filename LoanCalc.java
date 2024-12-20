// Computes the periodical payment necessary to pay a given loan.
public class LoanCalc {
	
	static double epsilon = 0.001;  // Approximation accuracy
	static int iterationCounter;    // Number of iterations 
	
	// Gets the loan data and computes the periodical payment.
    // Expects to get three command-line arguments: loan amount (double),
    // interest rate (double, as a percentage), and number of payments (int).  
	public static void main(String[] args) {		
		// Gets the loan data
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);
		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Computes the periodical payment using brute force search
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Computes the periodical payment using bisection search
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	// Computes the ending balance of a loan, given the loan amount, the periodical
	// interest rate (as a percentage), the number of periods (n), and the periodical payment.
	private static double endBalance(double loan, double rate, int n, double payment) {	
		double balance = loan;
		// Loop through each period to calculate the remaining balance:
		for (int i = 0; i < n; i++) {
			// Subtract the payment and apply the interest rate for the period:
			balance = (balance - payment) * (1 + (rate / 100));
		}
		return balance;
	}
	
	// Uses sequential search to compute an approximation of the periodical payment
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
        iterationCounter = 0;
        double payment = loan/n; // Start with a rough estimate of the payment

		// Increase the payment incrementally until the ending balance is close to zero:
        while (endBalance(loan, rate, n, payment) > 0) {
            payment += epsilon; // Increment the payment by the step size (epsilon)
            iterationCounter++; // Increment the iteration counter
        }
        return payment;
    }
    
    // Uses bisection search to compute an approximation of the periodical payment 
	// that will bring the ending balance of a loan close to 0.
	// Given: the sum of the loan, the periodical interest rate (as a percentage),
	// the number of periods (n), and epsilon, the approximation's accuracy
	// Side effect: modifies the class variable iterationCounter.
    public static double bisectionSolver(double loan, double rate, int n, double epsilon) {  
		iterationCounter = 0;
		double lo = 0; // Lower bound of the payment range
		double hi = loan; // Upper bound is loan amount (max possible payment)
		double g; // Midpoint of the current range

		// Narrow down the payment range until the difference is within epsilon
		while (hi - lo > epsilon) {
			g = (lo + hi) / 2;
			double balance = endBalance(loan, rate, n, g);

			if (balance > 0) {
				lo = g; // Payment is too low
			} else {
				hi = g; // Payment is too high
			}
			iterationCounter++;
		}
		return (lo + hi) / 2;
	}
}