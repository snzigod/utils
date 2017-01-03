package com.sn.utils.redis;

import org.springframework.util.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import com.sn.utils.base.SystemConfig;

/**
 * Redis 工具
 */
public final class RedisUtil {

	// Redis服务器IP
	private static String HOST = SystemConfig.getProperty("redis.host");

	// Redis的端口号
	private static int PORT = Integer.parseInt(SystemConfig
			.getProperty("redis.port"));

	// Redis访问密码
	private static String PASSWORD = SystemConfig.getProperty("redis.password");

	// Redis数据库
	private static int DATABASE = Integer.parseInt(SystemConfig
			.getProperty("redis.database"));

	// 可用连接实例的最大数目，默认值为8；
	// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
	private static int MAX_ACTIVE = Integer.parseInt(SystemConfig
			.getProperty("redis.maxActive"));

	// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
	private static int MAX_IDLE = Integer.parseInt(SystemConfig
			.getProperty("redis.maxIdle"));

	// 等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
	private static int MAX_WAIT = Integer.parseInt(SystemConfig
			.getProperty("redis.maxWait"));

	// 超时时间
	private static int TIMEOUT = Integer.parseInt(SystemConfig
			.getProperty("redis.maxWait"));

	// 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
	private static boolean TEST_ON_BORROW = Boolean.valueOf(SystemConfig
			.getProperty("redis.testOnBorrow"));

	private static JedisPool jedisPool = null;

	/**
	 * redis过期时间,以秒为单位
	 */
	public final static int EXRP_HOUR = 60 * 60; // 一小时
	public final static int EXRP_DAY = 60 * 60 * 24; // 一天
	public final static int EXRP_MONTH = 60 * 60 * 24 * 30; // 一个月

	/**
	 * 初始化Redis连接池
	 */
	private static void initialPool() {
		try {
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_ACTIVE);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(MAX_WAIT);
			config.setTestOnBorrow(TEST_ON_BORROW);
			jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT, PASSWORD,
					DATABASE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 在多线程环境同步初始化
	 */
	private static synchronized void poolInit() {
		if (jedisPool == null) {
			initialPool();
		}
	}

	/**
	 * 同步获取Jedis实例
	 * 
	 * @return Jedis
	 */
	public synchronized static Jedis getJedis() {
		if (jedisPool == null) {
			poolInit();
		}
		Jedis jedis = null;
		try {
			if (jedisPool != null) {
				jedis = jedisPool.getResource();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			returnResource(jedis);
		}
		return jedis;
	}

	/**
	 * 释放jedis资源
	 * 
	 * @param jedis
	 */
	public static void returnResource(final Jedis jedis) {
		if (jedis != null && jedisPool != null) {
			jedisPool.returnResource(jedis);
		}
	}

	/**
	 * 设置 String
	 * 
	 * @param key
	 * @param value
	 */
	public static void setValue(String key, String value) {
		try {
			value = StringUtils.isEmpty(value) ? "" : value;
			getJedis().set(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置过期时间
	 * 
	 * @param key
	 * @param seconds
	 *            以秒为单位
	 * @param value
	 */
	public static void setValue(String key, String value, int seconds) {
		try {
			value = StringUtils.isEmpty(value) ? "" : value;
			getJedis().setex(key, seconds, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取String值
	 * 
	 * @param key
	 * @return value
	 */
	public static String getString(String key) {
		if (getJedis() == null || !getJedis().exists(key)) {
			return null;
		}
		return getJedis().get(key);
	}

	/**
	 * 删除存在的String值
	 * 
	 * @param key
	 */
	public static void del(String key) {
		if (getJedis() == null || !getJedis().exists(key)) {
			return;
		}
		getJedis().del(key);
	}
}
