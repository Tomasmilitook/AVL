package imp;

import api.ABBTDA;

public class AVL implements ABBTDA {

	class NodoABB {
		int altura;  /* Agregamos la altura al nodo para poder verificar si esta balanceado */
		int info;
		ABBTDA hijoIzq;
		ABBTDA hijoDer;
	}
	
	NodoABB raiz;
	
	@Override
	public void inicializarArbol() {
		raiz = null;
	}

	@Override
	public void agregarElem(int x) {
		if (raiz == null) {
			raiz = new NodoABB();
			raiz.info = x;
			raiz.hijoIzq = new AVL();
			raiz.hijoIzq.inicializarArbol();
			raiz.hijoDer = new AVL();
			raiz.hijoDer.inicializarArbol();
		} else if (raiz.info > x ) {
			raiz.hijoIzq.agregarElem(x);
		} else if (raiz.info < x) {
			raiz.hijoDer.agregarElem(x);
		}
		actualizarAltura();
		balancear();
	}

	@Override
	public void eliminarElem(int x) {
		if (raiz != null) {
			if (raiz.info == x && raiz.hijoIzq.arbolVacio() && raiz.hijoDer.arbolVacio()) {
				raiz = null;
			} else if (raiz.info == x && !raiz.hijoIzq.arbolVacio()) {
				raiz.info = this.mayor(raiz.hijoIzq);
				raiz.hijoIzq.eliminarElem(raiz.info);
			} else if (raiz.info == x) {
				raiz.info = this.menor(raiz.hijoDer);
				raiz.hijoDer.eliminarElem(raiz.info);
			} if (raiz.info < x) {
				raiz.hijoDer.eliminarElem(x);
			} else {
				raiz.hijoIzq.eliminarElem(x);
			}
		}
		if (raiz != null) {
			actualizarAltura();
			balancear();
		}
	}
	
	
	private int altura(ABBTDA arbol) {
		if (arbol.arbolVacio()) return -1;
		return ((AVL) arbol).raiz.altura;
	}

	private void actualizarAltura() {
		raiz.altura = 1 + Math.max(altura(raiz.hijoIzq), altura(raiz.hijoDer));
	}
	
	private int balanceFactor() {
		return altura(raiz.hijoIzq) - altura(raiz.hijoDer);
	}

	private void balancear() {
		int balance = balanceFactor();

		if (balance > 1) {
			if (((AVL) raiz.hijoIzq).balanceFactor() < 0) {
				((AVL) raiz.hijoIzq).rotarIzquierda(); // Rotación doble
			}
			rotarDerecha();
		} else if (balance < -1) {
			if (((AVL) raiz.hijoDer).balanceFactor() > 0) {
				((AVL) raiz.hijoDer).rotarDerecha(); // Rotación doble
			}
			rotarIzquierda();
		}
	}
	
	private void rotarIzquierda() {
		NodoABB nuevaRaiz = ((AVL) raiz.hijoDer).raiz;
		ABBTDA subArbolIzquierdo = nuevaRaiz.hijoIzq;

		nuevaRaiz.hijoIzq = new AVL();
		((AVL) nuevaRaiz.hijoIzq).raiz = this.raiz;
		this.raiz.hijoDer = subArbolIzquierdo;

		this.raiz = nuevaRaiz;

		((AVL) raiz.hijoIzq).actualizarAltura();
		this.actualizarAltura();
	}

	private void rotarDerecha() {
		NodoABB nuevaRaiz = ((AVL) raiz.hijoIzq).raiz;
		ABBTDA subArbolDerecho = nuevaRaiz.hijoDer;

		nuevaRaiz.hijoDer = new AVL();
		((AVL) nuevaRaiz.hijoDer).raiz = this.raiz;
		this.raiz.hijoIzq = subArbolDerecho;

		this.raiz = nuevaRaiz;

		((AVL) raiz.hijoDer).actualizarAltura();
		this.actualizarAltura();
	}


	@Override
	public int raiz() {
		return raiz.info;
	}

	@Override
	public ABBTDA hijoIzq() {
		return raiz.hijoIzq;
	}

	@Override
	public ABBTDA hijoDer() {
		return raiz.hijoDer;
	}

	@Override
	public boolean arbolVacio() {
		return (raiz == null);
	}

	private int mayor(ABBTDA a){
		if (a.hijoDer().arbolVacio())
			return a.raiz();
		else
			return mayor(a.hijoDer());
	}
	
	private int menor(ABBTDA a){
		if (a.hijoIzq().arbolVacio())
			return a.raiz();
		else
			return menor(a.hijoIzq());
	}
}
