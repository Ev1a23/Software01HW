package il.ac.tau.cs.sw1.hw6;

public class Polynomial {
	
	/*
	 * Creates the zero-polynomial with p(x) = 0 for all x.
	 */
	private double[] coefficients_lst;

	public Polynomial()
	{
		this.coefficients_lst = new double[1];
		this.coefficients_lst[0] = 0.0;
	} 
	/*
	 * Creates a new polynomial with the given coefficients.
	 */
	public Polynomial(double[] coefficients) 
	{
		this.coefficients_lst  =  new double[coefficients.length];
		for (int i = 0; i < coefficients.length; i++) {
			this.coefficients_lst[i] = coefficients[i];
		}

	}
	/*
	 * Addes this polynomial to the given one
	 *  and retruns the sum as a new polynomial.
	 */
	public Polynomial adds(Polynomial polynomial)
	{
		int len = this.coefficients_lst.length;
		boolean flag = false;
		if (len<polynomial.coefficients_lst.length)
		{
			len = polynomial.coefficients_lst.length;
			flag = true;
		}
		double[] tmp = new double[len];
		int i = 0;
		if(!flag)
		{
			for(i = 0; i<polynomial.coefficients_lst.length; i++)
			{
				tmp[i] = this.coefficients_lst[i]+polynomial.coefficients_lst[i];
			}
			for (int j = i; j<this.coefficients_lst.length; j++)
			{
				tmp[j] = this.coefficients_lst[j];
			}

		}
		else
		{
			for(i = 0; i<this.coefficients_lst.length; i++)
			{
				tmp[i] = this.coefficients_lst[i]+polynomial.coefficients_lst[i];
			}
			for(int j = i; j<polynomial.coefficients_lst.length; j++)
			{
				tmp[j] = polynomial.coefficients_lst[j];
			}
		}
		Polynomial p = new Polynomial(tmp);
		return p;
	}
	/*
	 * Multiplies a to this polynomial and returns 
	 * the result as a new polynomial.
	 */
	public Polynomial multiply(double a)
	{
		double[] tmp = this.coefficients_lst.clone();
		for (int i = 0; i < tmp.length ; i++) {
			tmp[i] *= a;
		}
		 Polynomial p = new Polynomial(tmp);
		return p;

		
	}
	/*
	 * Returns the degree (the largest exponent) of this polynomial.
	 */
	public int getDegree()
	{
		int co = 0;
		for (int i = 0; i < this.coefficients_lst.length ; i++) {
			if(coefficients_lst[i] !=0)
			{
				co = i;
			}

		}
		return co;
	}
	/*
	 * Returns the coefficient of the variable x 
	 * with degree n in this polynomial.
	 */
	public double getCoefficient(int n)
	{
		if(n >this.getDegree())
		{
			return 0;

		}
		return this.coefficients_lst[n];
	}
	
	/*
	 * set the coefficient of the variable x 
	 * with degree n to c in this polynomial.
	 * If the degree of this polynomial < n, it means that the coefficient of the variable x
	 * with degree n was 0, and now it will change to c. 
	 */
	public void setCoefficient(int n, double c)
	{
		if(n>this.getDegree())
		{
			if(n>=this.coefficients_lst.length)
			{
				double[] tmp = new double[n+1];
				for(int i = 0; i<coefficients_lst.length; i++)
				{
					tmp[i] = coefficients_lst[i];
				}
				tmp[n] = c;
				this.coefficients_lst = tmp.clone();
			}
			else
			{
				this.coefficients_lst[n] = c;
			}
		}
		this.coefficients_lst[n] = c;
	}
	
	/*
	 * Returns the first derivation of this polynomial.
	 *  The first derivation of a polynomal a0x0 + ...  + anxn is defined as 1 * a1x0 + ... + n anxn-1.
	
	 */
	public Polynomial getFirstDerivation()
	{
		if (this.getDegree() == 0)
		{
			Polynomial p = new Polynomial();
			return p;
		}
		double[] der = new double[this.coefficients_lst.length-1];
		for(int i = 0; i< der.length; i++)
		{
			if(this.coefficients_lst[i+1] == 0)
			{
				der[i] = 0;
			}
			else
			{
					der[i] = this.coefficients_lst[i+1] * (i + 1);
			}
		}
		Polynomial p = new Polynomial(der);
		double[] tmp = new double[p.getDegree()+1];
		for (int i = 0; i < tmp.length; i++) {
			tmp[i] = der[i];
		}
		Polynomial n = new Polynomial(tmp);
		return n;
	}
	
	/*
	 * given an assignment for the variable x,
	 * compute the polynomial value
	 */
	public double computePolynomial(double x)
	{
		double val = 0.0;
		for (int i = 0; i<this.coefficients_lst.length; i++)
		{
			val+= this.coefficients_lst[i] *(Math.pow(x,i));
		}
		return val;
	}
	
	/*
	 * given an assignment for the variable x,
	 * return true iff x is an extrema point (local minimum or local maximum of this polynomial)
	 * x is an extrema point if and only if The value of first derivation of a polynomal at x is 0
	 * and the second derivation of a polynomal value at x is not 0.
	 */
	public boolean isExtrema(double x)
	{
		Polynomial der1 = this.getFirstDerivation();
		Polynomial der2 = der1.getFirstDerivation();
		if(der1.computePolynomial(x) == 0 && der2.computePolynomial(x)!=0)
		{
			return true;
		}
		return false;
	}
	
	
	
	

    
    

}
