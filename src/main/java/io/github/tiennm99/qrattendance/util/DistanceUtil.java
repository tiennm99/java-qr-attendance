package io.github.tiennm99.qrattendance.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.math3.util.FastMath;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DistanceUtil {
  public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
    final int R = 6371; // Radius of the earth in km
    double latDistance = FastMath.toRadians(lat2 - lat1);
    double lonDistance = FastMath.toRadians(lon2 - lon1);
    double a =
        FastMath.sin(latDistance / 2) * FastMath.sin(latDistance / 2)
            + FastMath.cos(FastMath.toRadians(lat1))
                * FastMath.cos(FastMath.toRadians(lat2))
                * FastMath.sin(lonDistance / 2)
                * FastMath.sin(lonDistance / 2);
    double c = 2 * FastMath.atan2(FastMath.sqrt(a), FastMath.sqrt(1 - a));
    return R * c; // Distance in km
  }
}
