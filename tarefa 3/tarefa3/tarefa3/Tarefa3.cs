using System;
using System.Collections;

namespace tarefa3
{
    class Tarefa3
    {
        static void Main(string[] args)
        {
            Tarefa3 P = new Tarefa3();
            //Limite inferior , limite superior, true = aberto | false = fechado
            Console.WriteLine("Aberto: " + P.calculate(0, 1, true));
            Console.WriteLine("Fechado: " + P.calculate(0, 1, false));
            
        }





        //Funções

        //Realiza o Calculo
        private string calculate(double inf_limit, double sup_limit,bool isOpen)
        {
            double error = 0.000001;
            double lastValue = aprx(inf_limit, sup_limit, 0, isOpen);
            double currentValue = aprx(inf_limit, sup_limit, 1, isOpen);
            int countInterations = 1;
            while(Math.Abs(lastValue - currentValue) > error)
            {
                countInterations++;
                lastValue = currentValue;
                currentValue = aprx(inf_limit, sup_limit, countInterations, isOpen);
            }
            return "Particoes: 2^" + countInterations + "\n" + "Valor Aproximado: " + currentValue;
        }

        //Aproximador
        private double aprx(double limitA, double limitB, int countDivision, bool isOpen)
        {

            double parcialSum = 0;
            double totalSum = 0;
            if (!isOpen)
            {
                int[] weigth = { 14, 64, 24, 64, 14 };
                int divisor = 45;

                double totalDivision = (int)Math.Pow(2 , countDivision);
                var dx = (limitB - limitA) / totalDivision;

                for (int i = 0; i < totalDivision; i++)
                {
                    var xi = limitA + i * dx;
                    var xf = xi + dx;
                    ArrayList tupla = h_closed(xi, xf);
                    double h = (double)tupla[0];
                    ArrayList points = (ArrayList)tupla[1];
                    parcialSum = 0;
                    for (int j = 0; j < weigth.Length ; j++)
                    {
                        parcialSum += weigth[j] * function((double)points[j]);
                    }
                    totalSum += parcialSum * h / divisor;

                }
                return totalSum;
            }
            else
            {
                int[] weigth = { 33, -42, 78, -42, 33 };
                int divisor = 10;

                double totalDivision = Math.Pow(2 , countDivision);
                var dx = (limitB - limitA) / totalDivision;

                for (int i = 0; i < totalDivision; i++)
                {
                    var xi = limitA + i * dx;
                    var xf = xi + dx;
                    ArrayList tupla = h_opened(xi, xf);
                    double h = (double)tupla[0];
                    ArrayList points = (ArrayList)tupla[1];
                    parcialSum = 0; parcialSum = 0;
                    for (int j = 0; j < weigth.Length; j++)
                    {
                        parcialSum += weigth[j] * function((double)points[j]);
                    }
                    totalSum += parcialSum * h / divisor;

                }
                return totalSum;
            }

        }

        //Calculo da Função sen(2x) + 4x² + 3x
        private double function(double point)
        {
           
            double result = (Math.Sin(2 * point) + (4 * point * point) + ((3 * point)));
            return result * result;
        }


        //H para calculo aberto e fechado
        private ArrayList h_opened(double inf_limit, double sup_limit)
        {
            ArrayList tuple = new ArrayList();
            var h = (sup_limit - inf_limit) / 6;
            ArrayList points = new ArrayList();
            for (int i = 0; i < 5; i++)
            {
                points.Add(inf_limit + h *(i+1));
            }

            tuple.Add(h);
            tuple.Add(points);
            return tuple;
        }
        private ArrayList h_closed(double inf_limit, double sup_limit)
        {
            ArrayList tuple = new ArrayList();
            var h = (sup_limit - inf_limit) / 4;
            ArrayList points = new ArrayList();
            for (int i = 0; i < 5; i++)
            {
                points.Add(inf_limit + h * i);
            }

            tuple.Add(h);
            tuple.Add(points);
            return tuple;
        }

    }
}
