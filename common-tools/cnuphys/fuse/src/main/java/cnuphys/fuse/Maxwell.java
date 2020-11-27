package cnuphys.fuse;

public class Maxwell {

	/**
	 * The maxwell energy distribution
	 * @param energy the energy in joules
	 * @param m the mass in kg
	 * @param T the temperature in Kelvin
	 * @return the value of the classical maxwell kinetic energy distribution
	 */
	public static double MaxwellE(double energy, double m, double T) {
		
		double kT = Constants.K*T;
		double f1 = Math.pow(m/(2*kT), 1.5);
		double f2 = Math.sqrt(2*energy/m);
		double f3 = Math.exp(-energy/kT);
		return (4/Constants.ROOTPI)*f1*f2*f3/m;
	}
}
