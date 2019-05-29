package Objet_base;
import java.awt.Graphics;

import javax.swing.tree.DefaultMutableTreeNode;

import P2D.Point2D;
import P2D.Vecteur2D;

public class Rectangle extends Triangle{
	private Point2D p3;

	public Rectangle(Point2D pOrigine, Point2D p1, Point2D p2, Point2D p3) {
		super(pOrigine, p1, p2);
		this.p3 = p3;
	}
	
	public Rectangle(int x, int y, int longeur, int largeur) {
		super(new Point2D(x,y),new Point2D(x+longeur, y), new Point2D(x, y+largeur));
		p3 = new Point2D(x+longeur, y+largeur);
	}
	
	public Rectangle(int x, int y, int longeur, int largeur, double angle) {
		super((new Point2D(x,y ) ),
						new Point2D((int)(x + longeur * Math.cos(Math.toRadians(angle) )) , (int) (y + longeur * Math.sin(Math.toRadians(angle))) 
								),
						new Point2D((int)(x - largeur * Math.sin(Math.toRadians(angle))) , (int) (y + largeur * Math.cos(Math.toRadians(angle))))
						);
						p3 = new Point2D((int) (x + (largeur * (-Math.sin(Math.toRadians(angle))) + (longeur * Math.cos(Math.toRadians(angle))))),
								(int) (y + (longeur * (Math.sin(Math.toRadians(angle))) + (largeur*Math.cos(Math.toRadians(angle)))) ));
						
	}

	public Point2D getP3() {
		return p3;
	}

	public void setP3(Point2D p3) {
		this.p3 = p3;
	}
	@Override
	public void afficher(Graphics g) {
		g.drawLine(this.getPOrigine().getX(), this.getPOrigine().getY(), this.getP1().getX(), this.getP1().getY());
		g.drawLine(this.getPOrigine().getX(), this.getPOrigine().getY(), this.getP2().getX(), this.getP2().getY());
		g.drawLine(this.getP3().getX(), this.getP3().getY(), this.getP1().getX(), this.getP1().getY());
		g.drawLine(this.getP3().getX(), this.getP3().getY(), this.getP2().getX(), this.getP2().getY());
	}

	@Override
	public void deplacer(Vecteur2D v) {
		this.setPOrigine( this.getPOrigine().appliquerVecteur(v) );
		this.setP1(this.getP1().appliquerVecteur(v));
		this.setP2(this.getP2().appliquerVecteur(v));
		this.setP3(this.getP3().appliquerVecteur(v));
		
	}
	
	@Override
	public boolean isIn(Point2D p) {
		int cmpt=0;
		int x = p.getX();
		int y = p.getY();
		boolean woah1 = true;
		boolean woah2 = true;
		boolean woah3 = true;
		boolean woah4 = true;
		for(int i=x;i<1920;i++) {
			
			
			
			if((Objet_de_base.dist(this.getPOrigine(),p)+Objet_de_base.dist(this.getP1(),p)==Objet_de_base.dist(this.getPOrigine(),this.getP1())) && woah1) {
				cmpt++;
				woah1 = (1==2);
				
		}
			if((Objet_de_base.dist(this.getPOrigine(),p)+Objet_de_base.dist(this.getP2(),p)==Objet_de_base.dist(this.getPOrigine(),this.getP2())) && woah2) {
				cmpt++;
				woah2 = (1==2);
				
		}
			if((Objet_de_base.dist(this.getP1(),p)+Objet_de_base.dist(this.getP3(),p)==Objet_de_base.dist(this.getP1(),this.getP3())) && woah3) {
				cmpt++;
				woah3 = false;
				
			}
			if((Objet_de_base.dist(this.getP2(),p)+Objet_de_base.dist(this.getP3(),p)==Objet_de_base.dist(this.getP2(),this.getP3())) && woah4) {
				cmpt++;
				woah4 = false;
				
			}
			p.setX(i);
			
		


	}
		return(cmpt==1);
		
	}
	
	public Objet_Geometrique dupliquer() { 
		return new Rectangle(this.getPOrigine(), this.getP1(), this.getP2(), this.p3); 
		}
	

@Override
public DefaultMutableTreeNode getNode() {
	DefaultMutableTreeNode node = new DefaultMutableTreeNode("Rectangle");
	node.add(this.getPOrigine().getNode("Po"));
	node.add(this.getP1().getNode("P1"));
	node.add(this.getP2().getNode("P2"));
	node.add(this.getP3().getNode("P3"));
	return node;
	
}

}
