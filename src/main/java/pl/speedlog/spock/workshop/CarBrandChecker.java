package pl.speedlog.spock.workshop;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:mariusz@wyszomierski.pl">Mariusz Wyszomierski</a>
 */
public class CarBrandChecker {

    private static final List<String> CAR_BRANDS = Arrays.asList("VW", "HONDA", "BMW");

    /**
     * Returns information about brand type and check if car is oldtimer.
     * @param carBrand car brand
     * @param productionYear production year
     * @return information
     */
    public static String check(String carBrand, int productionYear) {
        if (CAR_BRANDS.contains(carBrand)) {
            if ("HONDA".equals(carBrand) && productionYear <= 1975) {
                return "HONDA oldtimer";
            }
            if (productionYear <= 1970) {
                return carBrand + " oldtimer";
            }
            return carBrand;
        }
        return "Unknown brand";
    }

}
