package com.chendehe.etcd;

import java.util.Map;
import java.util.Set;

public interface ChangeConfigListener {

  void addConfig(Map<String, String> map);

  void delConfig(Set<String> key);

}
