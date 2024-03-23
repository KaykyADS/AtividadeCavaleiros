package controller;

import java.util.concurrent.Semaphore;

public class ThreadController extends Thread {

	private int idThread;
	private Semaphore semaforo;
	private Semaphore semaforo2 = new Semaphore(1);
	private Semaphore semaforo3 = new Semaphore(1);
	private int caminhada = 0;
	private static int cont = 0;
	private static int cont2 = 0;
	private int porta = 0;
	private static int portaCont = 1;
	private static int portaCerta = (int) (Math.random() * 4) + 1;

	public ThreadController(int idThread, Semaphore semaforo) {
		super();
		this.idThread = idThread;
		this.semaforo = semaforo;

	}

	@Override
	public void run() {
		Caminhada();
		try {
			semaforo2.acquire();
			porta();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			semaforo2.release();
		}
	}

	public void Pedra() {
		System.out.println("O cavaleiro " + idThread + " pegou a pedra!");
		while (caminhada <= 2000) {
			caminhada += (int) ((Math.random() * 3) + 2) + 2;
			try {
				sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("O cavaleiro " + idThread + " caminhou " + caminhada + " metros.");
		}
		System.out.println("O cavaleiro " + idThread + " chegou e abriu a porta...");
	}

	public void Tocha() {
		System.out.println("O cavaleiro " + idThread + " pegou a tocha!");
		while (caminhada <= 2000) {
			caminhada += (int) ((Math.random() * 3) + 2) + 2;
			try {
				sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("O cavaleiro " + idThread + " caminhou " + caminhada + " metros.");
		}
		System.out.println("O cavaleiro " + idThread + " chegou e abriu a porta...");
	}

	public void Caminhada() {
		while (caminhada <= 2000) {
			caminhada += (int) (Math.random() * 3) + 2;
			try {
				sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (cont == 0) {
				if (caminhada > 499) {
					cont = 1;
					try {
						semaforo.acquire();
						Tocha();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						semaforo.release();
					}
				}
			} else if (cont2 == 0) {
				if (caminhada > 1499) {
					cont2 = 1;
					try {
						semaforo3.acquire();
						Pedra();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						semaforo3.release();
					}
				}
			}
			System.out.println("O cavaleiro " + idThread + " caminhou " + caminhada + " metros.\n");
		}
		System.out.println("O cavaleiro " + idThread + " chegou e abriu a porta...");
	}

	public void porta() {
		porta = portaCont;
		portaCont++;
		if (porta == portaCerta) {
			System.out.println("O cavaleiro " + idThread + " sobreviveu e os outros foram devorados!!!\n");
		} else {
			System.out.println("O cavaleiro " + idThread + " morreu tentando...\n");
		}
	}
}