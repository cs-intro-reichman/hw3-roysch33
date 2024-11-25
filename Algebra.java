// Implements algebraic operations and the square root function without using 
// the Java operations a + b, a - b, a * b, a / b, a % b, and without calling 
// Math.sqrt. All the functions in this class operate on int values and
// return int values.

public class Algebra {
	public static void main(String args[]) {
	    // Tests some of the operations
	    System.out.println(plus(2,3));   // 2 + 3
	    System.out.println(minus(7,2));  // 7 - 2
   		System.out.println(minus(2,7));  // 2 - 7
 		System.out.println(times(3,4));  // 3 * 4
   		System.out.println(plus(2,times(4,2)));  // 2 + 4 * 2
   		System.out.println(pow(5,3));      // 5^3
   		System.out.println(pow(3,5));      // 3^5
   		System.out.println(div(12,3));   // 12 / 3    
   		System.out.println(div(5,5));    // 5 / 5  
   		System.out.println(div(25,7));   // 25 / 7
   		System.out.println(mod(25,7));   // 25 % 7
   		System.out.println(mod(120,6));  // 120 % 6    
   		System.out.println(sqrt(36));
		System.out.println(sqrt(263169));
   		System.out.println(sqrt(76123));
	}  

	// Returns x1 + x2
    public static int plus(int x1, int x2) {
        int counting = x1;
        int secondNum = x2;
        // If secondNum is positive, run the loop while "secondNum" times and add to counting 1:
        while (secondNum > 0) {
            counting++;
            secondNum--;
        }
    
        // If secondNum is negative, run the loop while |"secondNum"| times and substract from counting 1:
        while (secondNum < 0) {
            counting--;
            secondNum++;
        }
        return counting;
    }

    // Returns x1 - x2
    public static int minus(int x1, int x2) {
        int counting = x1;
        int secondNum = x2;
        // If secondNum is positive, run the loop while "secondNum" times and substract from counting 1:
        while (secondNum > 0) {
            counting--;
            secondNum--;
        }
    
        // If secondNum is negative, run the loop while |"secondNum"| times and add to counting 1:
        while (secondNum < 0) {
            counting++;
            secondNum++;
        }
        return counting;
    }

    // Returns x1 * x2
    public static int times(int x1, int x2) {
        // Replace the following statement with your code
        int firstNum = x1;
        int secondNum = x2;
        int counting = 0;


        for (int i = 0; i > secondNum; i-- ){
            counting = minus(counting,firstNum);
            }
            
        for (int i = 0; i < secondNum; i++ ){
            counting = plus(counting,firstNum);
        }

        
        return counting;
    }

    // Returns x^n (for n >= 0)
    public static int pow(int x, int n) {
        int firstNum = x;
        int secondNum = n;
        int counting = firstNum;
        // x^0 is always 0:
        if (n == 0){
            return 1;
        }
        for (int i = 0; i < secondNum-1; i++ ){
            counting = times(counting,firstNum);
        }
        return counting;
    }

    // Returns the integer part of x1 / x2 
    public static int div(int x1, int x2) {
        int firstNum = x1;
        int secondNum = x2;
        int counting = 0;
        int ifPositive = 1;
    
        // Adjust the ifPositive based on the inputs
        if (firstNum < 0) {
            firstNum = -firstNum;
            ifPositive = -ifPositive;
        }
        if (secondNum < 0) {
            secondNum = -secondNum;
            ifPositive = -ifPositive;
        }
    
        // Subtract secondNum from firstNum while counting the number of subtractions:
        while (firstNum >= secondNum) {
            firstNum -= secondNum;
            counting++;
        }
    
        // Apply the ifPositive to the result
        return times(ifPositive, counting);
    }

    // Returns x1 % x2
    public static int mod(int x1, int x2) {
        int firstNum = x1;
        int secondNum = x2;
        // if firstNum is >= to secondNum then substract secondNum to it antill it fall in range: 
        while (firstNum >= secondNum) {
            firstNum -= secondNum; 
        }
        // if firstnum is negetive then we add secondNum to it to make it positive:
        while (firstNum < 0) {
            firstNum += secondNum; 
        }

        return firstNum;
    }   

    // Returns the integer part of sqrt(x) 
    public static int sqrt(int x) {
        int firstNum = x;
        int counting = 0;
        if (firstNum == 1){
            return 1;
        }
        while (firstNum != counting) {
            if (times(counting,counting) >= firstNum) {
                if (times(counting,counting) == firstNum) {
                    return counting;
                }
                else {
                    return (counting-1);
                }
            }
            else {
                counting ++;
            }
        }
        return 0;
    }         
}   