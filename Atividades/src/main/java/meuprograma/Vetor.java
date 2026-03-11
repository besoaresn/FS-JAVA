package meuprograma;

import java.util.Scanner;

public class Vetor {
    int N;
    float[] vt;
    float maiorV, menorV, soma, media;


    public void entrada() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        vt = new float[N];
    }

    public void entradaVetor() {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < N; i++){
            System.out.println("Digite o valor do vetor na posição " + i + ":");
            vt[i] = sc.nextFloat();
        }
    }

    public void calculo(){
        maiorV = vt[0];
        menorV = vt[0];
        soma = vt[0];

        for ( int i = 1; i < N; i++){
            if ( vt[i] > maiorV)
                maiorV = vt[i];
            if ( vt[i] < menorV)
                menorV = vt[i];

            soma += vt[i];
        }
        media = soma / N;
    }
    public void imprimeResult(){
        System.out.println("Maior Valor: " + maiorV);
        System.out.println("Menor Valor: " + menorV);
        System.out.println("Média: " + media);
        System.out.println("Soma: " + soma);
    }
}
