package Bank;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

    public class BankMain {
        private static Clients parsePersonObject(JSONObject client) {
            Clients newClient = new Clients(null, null, null);

            ArrayList<Account> acc = new ArrayList<Account>();

            try {
                newClient.name = (String) client.get("Name");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            newClient.surname = (String) client.get("Surname");

            JSONArray jsonArray = (JSONArray)client.get("Account");

            for (int i = 0; i <= jsonArray.size()-1; i++){
                var arr = (JSONArray)(jsonArray.get(i));
                Account account = new Account(Integer.parseInt((String)arr.get(0)),Double.parseDouble((String)arr.get(1)));
                acc.add(account);
            }
            newClient.accounts = acc;
            return newClient;
        }

        private static Transactions parseTransactionObject(JSONArray transaction, ArrayList<Clients> clients) throws AmountException {
            var fromNumber = Integer.parseInt((String)transaction.get(0));
            var toNumber = Integer.parseInt((String)transaction.get(1));
            Account from = null;
            Account to = null;
            for (int i = 0; i<=clients.size()-1; i++) {
                for (int j = 0; j <= clients.get(i).accounts.size()-1; j++) {
                    if ((clients.get(i).accounts.get(j).account) == fromNumber) {
                        from = clients.get(i).accounts.get(j);
                    }
                    if ((clients.get(i).accounts.get(j).account) == toNumber) {
                        to = clients.get(i).accounts.get(j);
                    }
                }
            }
            double sum = Double.parseDouble((String)transaction.get(2));
            from.amount = from.amount + sum;
            to.amount = to.amount - sum;
            Transactions transactionObject = new Transactions(from, to, sum, (String)transaction.get(3));
            return transactionObject;
        }

        public static void WriteFile(String fileName, JSONObject object){
            try {
                FileWriter fw = new FileWriter(fileName);
                fw.write(object.toJSONString());
                fw.flush();
                fw.close();
            } catch (Exception e)
            {
                System.out.println("Exception caught!");
            }
        }

        public static List<List<String>> ConvertAcountARRAY(Clients client){
            List<List<String>> array = new ArrayList<List<String>>();
            for (int i = 0; i < client.accounts.size(); i++){
                array.add(Arrays.asList(String.valueOf(client.accounts.get(i).account), String.valueOf(client.accounts.get(i).amount)));
            }
            return array;
        }

        public static ArrayList<String> ConvertTransactionsARRAY(Transactions transactions){
            ArrayList<String> array = new ArrayList<String>();
            array.addAll(Arrays.asList(String.valueOf(transactions.from)));
            array.addAll(Arrays.asList(String.valueOf(transactions.to)));
            array.addAll(Arrays.asList(String.valueOf(transactions.Sum)));
            array.addAll(Arrays.asList(String.valueOf(transactions.date)));
            return array;
        }

        public static ArrayList<Clients> ClientsReader(){
            ArrayList<Clients> clients = new ArrayList<Clients>();
            JSONParser parser = new JSONParser();
            try (FileReader reader = new FileReader("C:\\Артур\\Программирования\\Java\\Homework\\clients.json")){
                JSONObject root = (JSONObject)parser.parse(reader);
                JSONArray clientList = (JSONArray) root.get("Clients");
                for (int i = 0; i <= clientList.size()-1;i++) {
                    clients.add(parsePersonObject((JSONObject) clientList.get(i)));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return clients;
        }

        public static ArrayList<Transactions> TransactionsReader(ArrayList<Clients> clients){
            ArrayList<Transactions> transactions = new ArrayList<Transactions>();
            JSONParser parser = new JSONParser();
            try (FileReader reader = new FileReader("C:\\Артур\\Программирования\\Java\\Homework\\transactions.json")){
                JSONObject root = (JSONObject)parser.parse(reader);
                JSONArray transactionsList = (JSONArray) root.get("Transactions");
                for (int i = 0; i <= transactionsList.size()-1;i++) {
                    transactions.add(parseTransactionObject((JSONArray)transactionsList.get(i),clients));
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }catch (AmountException e) {
                e.printStackTrace();
            }

            return transactions;
        }

        public static JSONArray PUTClient(ArrayList<Clients> clients) {
            JSONArray clientsArray = new JSONArray();
            for (int i = 0; i < clients.size(); i++){
                JSONObject client = new JSONObject();
                client.put("Name", clients.get(i).name);
                client.put("Surname", clients.get(i).surname);
                client.put("Account", ConvertAcountARRAY(clients.get(i)));
                clientsArray.add(client);
            }
            return clientsArray;
        }

        public static ArrayList<ArrayList<String>> PUTTransaction(ArrayList<Transactions> transactions) {
            ArrayList<ArrayList<String>> transactionArrayList = new ArrayList<ArrayList<String>>();
            for (int i = 0; i <= transactions.size()-1; i++) {
                transactionArrayList.addAll(Arrays.asList(ConvertTransactionsARRAY(transactions.get(i))));
            }
            return transactionArrayList;
        }

        public static void main(String[] args) throws AmountException, FileNotFoundException {
            Bank bank = new Bank();

            ArrayList<Clients> Clients = new ArrayList<Clients>(ClientsReader());
            ArrayList<Transactions> Transactions = new ArrayList<Transactions>(TransactionsReader(Clients));

            JSONArray clientsArray = PUTClient(Clients);

            JSONObject mainClientObj = new JSONObject();
            mainClientObj.put("Clients", clientsArray);

            JSONObject mainTransactionsObj = new JSONObject();
            List<List<String>> transactionArrayList = new ArrayList<List<String>>(PUTTransaction(Transactions));

            mainTransactionsObj.put("Transactions", transactionArrayList);
            System.out.println(mainClientObj.toJSONString());
            System.out.println(mainTransactionsObj.toJSONString());
            WriteFile("clients.json",mainClientObj);
            WriteFile("transactions.json",mainTransactionsObj);

        }
    }
