package com.chendehe.etcd;

public final class EtcdConfig {

  private String url;

  public EtcdConfig(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  public static ConfigBuilder builder() {
    return new ConfigBuilder();
  }

  public static class ConfigBuilder {

    private String url;

    public ConfigBuilder setUrl(String url) {
      this.url = url;
      return this;
    }

    public EtcdConfig build() {
      return new EtcdConfig(url);
    }

  }

}
