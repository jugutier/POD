package math;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class ServiceImpl implements Service
{
	private int i=0;
    public ServiceImpl() throws RemoteException
    {
        UnicastRemoteObject.exportObject(this, 0);
    }

    private void check(int[] elements)
    {
        if (elements == null || elements.length== 0)
            throw new RuntimeException("Arreglo inválido");
    }

    @Override
    public double getMediana(int[] elements) throws RemoteException
    {
        String s = String.format("%s (%d) vble=%d", Thread.currentThread().getName(), Thread.currentThread().getId(), i++ );
        System.out.println(s);
        try{
            Thread.sleep(30*1000);//30sec

        }catch(InterruptedException e){
            e.printStackTrace();
        }
        check(elements);
        Arrays.sort(elements);
        if (  elements.length % 2 == 0 )
            return (elements[elements.length / 2 - 1 ] + elements[elements.length / 2] ) / 2.0;
        return elements[ elements.length / 2  ];
    }

    @Override
    public int[] getModa(int[] elements) throws RemoteException
    {
        check(elements);

        int[] resp = new int[elements.length];

        Map<Integer,Integer> m = new HashMap<Integer,Integer>();

        for (int elemento: elements){
            if (m.containsKey(elemento))
                m.put(elemento,m.get(elemento)+1);
            else
                m.put(elemento,1);
        }

        int repeticiones = 0;
        List<Integer> moda = new ArrayList<Integer>();

        Iterator<Entry<Integer, Integer>> iter = m.entrySet().iterator();


        while (iter.hasNext()) {
            Entry<Integer,Integer> e = iter.next();

            if (e.getValue() > repeticiones) {
                moda.clear();
                moda.add(e.getKey());
                repeticiones = e.getValue();
            } else if (e.getValue() == repeticiones)
                moda.add(e.getKey());
        }

        int i = 0;
        for(Integer valor:moda){
            resp[i++] = valor;
        }

        if (moda.size() == elements.length)
            return null;
        else
            return resp;
    }

    @Override
    public double getPromedio(int[] elements) throws RemoteException
    {
        check(elements);

        double suma = 0;

        for(int e:elements){
            suma+=e;
        }

        double promedio = suma/elements.length;

        return promedio;
    }
}

