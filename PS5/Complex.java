
public class Complex {
	
	private double real;
	private double ima;
	
	public Complex(){
	}
	
	public Complex(double num){
		real = num;
	}
	
	public Complex(double num, double ima){
		real = num;
		this.ima = ima;
	}
	
	public Complex(String num){
		num = num.replaceAll("\\s", ""); //Removes Spaces
		
		int sign = -1;
		int iIndex = num.indexOf('i');
		
		if(num.indexOf('-')>0 || num.indexOf('+')>0)
			sign = Math.max(num.indexOf('-'), num.indexOf('+')); //Ensures that sign is not set to the index of 0 if the initial part is negative
		
		if(iIndex < 0 && sign < 0){
			try{
				real = (double)Double.parseDouble(num);
			} catch(Exception e){
				System.out.println("Check input");
			}
		}else if(sign < 0 && iIndex > 0){
			try{
				ima = Double.parseDouble(num.substring(0, iIndex));
			} catch(Exception e){
				System.out.println("Check input");
			}
		} else {
			if(iIndex < sign){
				try{
					ima = Double.parseDouble(num.substring(0, iIndex));
					real = Double.parseDouble(num.substring(sign+1, num.length()));
				} catch(Exception e){
					System.out.println("Check input");
				}
			}
			else {
				try{
					real = Double.parseDouble(num.substring(0, sign));
					ima = Double.parseDouble(num.substring(sign+1, iIndex));
				} catch(Exception e){
					System.out.println("Check input");
				}
			}
		}
	}
	
	public boolean equals(Object b){
		try{
			Complex c = (Complex)b;
			return c.real == this.real && c.ima == this.ima;
		}
		catch(Exception a){
			return false;
		}
	}
	
	public void copy(Complex b){
		if (b == null)
		      real = ima = 0;
		    else {
		      real = b.real;
		      ima = b.ima;
		    }
	}
	
	public Complex copy(){
		return new Complex(this.real, this.ima);
	}
	
	public double getImaginary(){
		return ima;
	}
	
	public double getReal(){
		return real;
	}
	
	public void setImaginary(double a){
		ima = a;
	}
	
	public void setReal(double a){
		real = a;
	}
	
	public Complex add(double b){
		Complex a = new Complex(b);
		return this.add(a);
	}
	
	public Complex add(Complex b){
		return new Complex(this.real + b.real, this.ima + b.ima);
	}
	
	public Complex add(Complex b, Complex c){
		return new Complex(b.real + c.real, b.ima + c.ima);
	}
	
	public Complex subtract(Complex b){
		return new Complex(this.real - b.real, this.ima - b.ima);
	}
	
	public Complex subtract(Complex b, Complex c){
		return new Complex(b.real - c.real, b.ima - c.ima);
	}
	
	public Complex multiply(double b){
		return new Complex(this.real * b, this.ima * b);
	}
	
	public Complex multiply(Complex b){
		return new Complex(this.real * b.real - this.ima * b.ima, this.real * b.ima + this.ima * b.real);
	}
	
	public Complex multiply(Complex b, Complex c){
		return new Complex(c.real * b.real - c.ima * b.ima, c.real * b.ima + c.ima * b.real);
	}
	
	public Complex divide(double b){
		return new Complex(this.real/b, this.ima/b);
	}
	
	public Complex divide(Complex b){
		Complex a = this;
		return a.multiply(b.reciprocal());
	}
	
	public Complex conjugate(){
		return new Complex(this.real, -this.ima);
	}
	
	public Complex reciprocal(){
		double num = Math.pow(this.real, 2) + Math.pow(this.ima, 2);
		return new Complex(this.real/num, -this.ima/num);
	}
	
	 public Complex sin(){
	        return new Complex(Math.sin(real) * Math.cosh(ima), Math.cos(real) * Math.sinh(ima));
	    }
	 
	 public Complex cos(){
		 return new Complex(Math.cos(real) * Math.cosh(ima), -Math.sin(real) * Math.sinh(ima));
	 }
	 
	 public Complex tan(){
		 return this.sin().divide(this.cos());
	 }
	 
	 public Complex pwr(double x){
		 double modulus = Math.sqrt(Math.pow(real, 2) + Math.pow(ima, 2));
		 double arg = Math.atan2(ima,real);
		 double realLog = Math.log(modulus);
		 double imaginaryLog = arg;
		 double realX = x*realLog;
		 double imaX = x*imaginaryLog;
		 double mod = Math.exp(realX);
		 return new Complex(mod*Math.cos(imaX), mod*Math.sin(imaX));
	 }
	 
	 public Complex polar(double r, double theta){
		 return new Complex(r*Math.cos(theta), r*Math.sin(theta));
	 }
	 
	 public double r(){
		 return Math.sqrt(real*real + ima*ima);
	 }
	 
	 public double theta(){
		 return Math.atan2(ima, real);
	 }
	 
	 public Complex root(int k){
		    double a,b;
		    boolean neg = false;
		    if(k < 0){
		      k = -k;
		      neg = true;
		    }
		    if(k == 0){ 
		      a = 1;
		      b = 0;
		    }
		    else if(k == 1){
		      a = real;
		      b = ima;
		    }
		    else {
		      double length = r();
		      double angle = theta();
		      if(angle < 0)
		        angle += Math.PI*2;
		      length = Math.pow(length,1.0/k);
		      angle = angle / k;
		      a = length*Math.cos(angle);
		      b = length*Math.sin(angle);
		    }
		    if(neg){
		      double denom = a*a + b*b;
		      a = a/denom;
		      b = -b/denom;
		    }
		    return new Complex(a,b);
	 }
	
	public String toString(){
        if (ima == 0) return real + "";
        if (real == 0) return ima + "i";
        if (ima <  0) return real + " - " + (-ima) + "i";
        return real + " + " + ima + "i";
    }
	
	//For testing purposes
	public static void main(String[] args){
		Complex a = new Complex(5.0, -6.0);
        Complex b = new Complex(-3.0, 4.0);
        Complex c = new Complex();
        
        String s = "3 + 5i";
        String t = "2i + 4";
        String u = "4i";
        String v = "37";
        
        Complex w = new Complex(s);
        Complex x = new Complex(t);
        Complex y = new Complex(u);
        Complex z = new Complex(v);

        System.out.println("a            = " + a);
        System.out.println("b            = " + b);
        System.out.println("w            = " + w);
        System.out.println("x            = " + x);
        System.out.println("y            = " + y);
        System.out.println("z            = " + z);
        System.out.println("Real(a)      = " + a.getReal());
        System.out.println("Imaginary(a) = " + a.getImaginary());
        System.out.println("b + a        = " + b.add(a));
        System.out.println("a - b        = " + a.subtract(b));
        System.out.println("a * b        = " + a.multiply(b));
        System.out.println("b * a        = " + b.multiply(a));
        System.out.println("a / b        = " + a.divide(b));
        System.out.println("(a / b) * b  = " + a.divide(b).multiply(b));
        System.out.println("conj(a)      = " + a.conjugate());
	}

}
