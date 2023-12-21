package algorithms;

import java.awt.Point;
import java.util.ArrayList;

import supportGUI.Circle;
import supportGUI.Line;

public class DefaultTeam {

  public Line calculDiametre(ArrayList<Point> points) {
    Line diametre = null;
    double maxDistance = 0;

    // Recherche du diamètre par chaque paire de points
    for (int i = 0; i < points.size(); i++) {
      for (int j = i + 1; j < points.size(); j++) {
        Point p = points.get(i);
        Point q = points.get(j);

        double distance = p.distance(q);

        // Si la distance entre les points est la plus grande trouvée jusqu'à présent
        if (distance > maxDistance) {
          maxDistance = distance;
          diametre = new Line(p, q);
        }
      }
    }

    return diametre;
  }

  public Circle calculCercleMin(ArrayList<Point> points) {
    if (points.size()<1) return null;
    ArrayList<Point> rest = (ArrayList<Point>)points.clone();
    Point dummy=rest.get(0);
    Point p=dummy;
    for (Point s: rest) if (dummy.distance(s)>dummy.distance(p)) p=s;
    Point q=p;
    for (Point s: rest) if (p.distance(s)>p.distance(q)) q=s;
    double cX=.5*(p.x+q.x);
    double cY=.5*(p.y+q.y);
    double cRadius=.5*p.distance(q);
    rest.remove(p);
    rest.remove(q);
    while (!rest.isEmpty()){
      Point s=rest.remove(0);
      double distanceFromCToS=Math.sqrt((s.x-cX)*(s.x-cX)+(s.y-cY)*(s.y-cY));
      if (distanceFromCToS<=cRadius) continue;
      double cPrimeRadius=.5*(cRadius+distanceFromCToS);
      double alpha=cPrimeRadius/(double)(distanceFromCToS);
      double beta=(distanceFromCToS-cPrimeRadius)/(double)(distanceFromCToS);
      double cPrimeX=alpha*cX+beta*s.x;
      double cPrimeY=alpha*cY+beta*s.y;
      cRadius=cPrimeRadius;
      cX=cPrimeX;
      cY=cPrimeY;
    }
    return new Circle(new Point((int)cX,(int)cY),(int)cRadius);
  }
}