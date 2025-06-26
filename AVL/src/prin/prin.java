package prin;

import api.ABBTDA;
import imp.ABB;
import imp.AVL;

public class prin {

	public static int contarNodos(ABBTDA a) {
		int resultado;
		if (a.arbolVacio())
			resultado = 0;
		else
			resultado = 1 + contarNodos(a.hijoIzq()) + contarNodos(a.hijoDer());
		return resultado;
	}
	
	public static int altura(ABBTDA a) {
		int resultado;
		if (a.arbolVacio())
			resultado = -1;
		else
			resultado = 1 + Math.max(altura(a.hijoIzq()), altura(a.hijoDer()));
		return resultado;
	}
	
	public static void preOrder(ABBTDA a) {
		if(!a.arbolVacio()) {
			System.out.print(a.raiz() + " ");
			preOrder(a.hijoIzq());
			preOrder(a.hijoDer());
		}
	}
	
	public static void inOrder(ABBTDA a) {
		if(!a.arbolVacio()) {
			inOrder(a.hijoIzq());
			System.out.print(a.raiz() + " ");
			inOrder(a.hijoDer());
		}
	}
	
	public static void postOrder(ABBTDA a) {
		if(!a.arbolVacio()) {
			postOrder(a.hijoIzq());
			postOrder(a.hijoDer());
			System.out.print(a.raiz() + " ");
		}
	}
	
	
	public static void main(String[] args) {
		ABBTDA sinBalanceo = new ABB();
		ABBTDA conBalanceo = new AVL();
		
		sinBalanceo.inicializarArbol();
		sinBalanceo.agregarElem(10);
		sinBalanceo.agregarElem(20);
		sinBalanceo.agregarElem(30);
		sinBalanceo.agregarElem(40);
		sinBalanceo.agregarElem(50);
		sinBalanceo.agregarElem(60);
		
		System.out.println("En este caso vamos a utilizar la misma secuencia de inserciones\nen un arbol con AVL y otro sin AVL.\nLas inserciones seran de 10 en 10, del 10 al 60.\nEl arbol sin AVL deberia dar una altura igual a cantidad de inserciones - 1.\nMientras que el AVL deberia reducirla ");
		System.out.println();
		
		System.out.println("----------Arbol ABB sin AVL--------");
		System.out.println();
		System.out.println("pre-order:");
		preOrder(sinBalanceo);
		System.out.println();
		System.out.println();
		System.out.println("in-order:");
		inOrder(sinBalanceo);
		System.out.println();
		System.out.println();
		System.out.println("post-order:");
		postOrder(sinBalanceo);
		System.out.println();
		System.out.println();
		System.out.println("La cantidad de nodos es " + contarNodos(sinBalanceo));
		System.out.println("La altura del árbol es " + altura(sinBalanceo));
		
		conBalanceo.inicializarArbol();
		conBalanceo.agregarElem(10);
		conBalanceo.agregarElem(20);
		conBalanceo	.agregarElem(30);
		conBalanceo	.agregarElem(40);
		conBalanceo	.agregarElem(50);
		conBalanceo	.agregarElem(60);
		
		System.out.println();
		System.out.println("----------Arbol ABB con AVL--------");
		System.out.println();
		System.out.println("pre-order:");
		preOrder(conBalanceo);
		System.out.println();
		System.out.println();
		System.out.println("in-order:");
		inOrder(conBalanceo);
		System.out.println();
		System.out.println();
		System.out.println("post-order:");
		postOrder(conBalanceo);
		System.out.println();
		System.out.println();
		System.out.println("La cantidad de nodos es " + contarNodos(conBalanceo));
		System.out.println("La altura del árbol es " + altura(conBalanceo));
	}
}
