package com.chendehe.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

public final class EsUtil {

  private EsUtil() {
    System.out.println("===");
  }

  public static void main(String[] args) {

    System.out.println("=start1==");
    try (Client client = new PreBuiltTransportClient(Settings.EMPTY)
        .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300))) {

      System.out.println("=start==");
      GetResponse response = client.prepareGet("megacorp", "employee", "1").get();
      System.out.println(response);
      System.out.println("=end==");


    } catch (UnknownHostException e) {
      e.printStackTrace();
    } finally {
      System.out.println("=finally==");
    }
  }
}
