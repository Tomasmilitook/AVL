package prin;

import api.ABBTDA;
import imp.ABB;
import imp.AVL;

public class prin {

 public static int contarNodos(ABBTDA a) {
  if (a.arbolVacio()) return 0;
  return 1 + contarNodos(a.hijoIzq()) + contarNodos(a.hijoDer());
 }

 public static int altura(ABBTDA a) {
  if (a.arbolVacio()) return -1;
  return 1 + Math.max(altura(a.hijoIzq()), altura(a.hijoDer()));
 }

 public static void preOrder(ABBTDA a) {
  if (!a.arbolVacio()) {
   System.out.print(a.raiz() + " ");
   preOrder(a.hijoIzq());
   preOrder(a.hijoDer());
  }
 }

 public static void inOrder(ABBTDA a) {
  if (!a.arbolVacio()) {
   inOrder(a.hijoIzq());
   System.out.print(a.raiz() + " ");
   inOrder(a.hijoDer());
  }
 }

 public static void postOrder(ABBTDA a) {
  if (!a.arbolVacio()) {
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

  System.out.println("En este caso vamos a utilizar la misma secuencia de inserciones"
    + "\nen un árbol con AVL y otro sin AVL."
    + "\nLas inserciones serán de 10 en 10, del 10 al 60."
    + "\nEl árbol sin AVL debería dar una altura igual a cantidad de inserciones - 1."
    + "\nMientras que el AVL debería reducirla.\n");

  System.out.println("---------- Árbol ABB sin AVL ----------");
  System.out.println("Pre-order:");
  preOrder(sinBalanceo);
  System.out.println("\n\nIn-order:");
  inOrder(sinBalanceo);
  System.out.println("\n\nPost-order:");
  postOrder(sinBalanceo);
  System.out.println("\n\nCantidad de nodos: " + contarNodos(sinBalanceo));
  System.out.println("Altura del árbol: " + altura(sinBalanceo));

  conBalanceo.inicializarArbol();
  conBalanceo.agregarElem(10);
  conBalanceo.agregarElem(20);
  conBalanceo.agregarElem(30);
  conBalanceo.agregarElem(40);
  conBalanceo.agregarElem(50);
  conBalanceo.agregarElem(60);

  System.out.println("\n\n---------- Árbol ABB con AVL ----------");
  System.out.println("Pre-order:");
  preOrder(conBalanceo);
  System.out.println("\n\nIn-order:");
  inOrder(conBalanceo);
  System.out.println("\n\nPost-order:");
  postOrder(conBalanceo);
  System.out.println("\n\nCantidad de nodos: " + contarNodos(conBalanceo));
  System.out.println("Altura del árbol: " + altura(conBalanceo));
 }
}
