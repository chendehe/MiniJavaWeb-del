package com.chendehe.etcd;

import java.util.Map;

public interface ChangeConfigListener {

  void receiveConfig(Map<String, String> map);

}
