package cn.sigo.sigocloudprovider.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * 封装redis 缓存服务器服务接口 B2Badmin
 */
public class RedisService {

	private JedisPool jedisPool;

	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public Jedis getJedis() {
		Jedis jedis = null;
		try {
			jedis = getJedisPool().getResource();
		} catch (Exception e) {
			throw new JedisConnectionException(e);
		}
		return jedis;
	}

	public void returnResource(Jedis jedis, boolean isBroken) {
		if (jedis == null)
			return;
		if (isBroken)
			getJedisPool().returnBrokenResource(jedis);
		else
			getJedisPool().returnResource(jedis);
	}

	/************************************************** common *******************************************************/

	//设置key的失效时间
	public Long expire(String key,int secounds) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		Long result;
		try {
			jedis = getJedis();
			result = jedis.expire(key, secounds);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
		return result;
	}

	/************************************************** String *******************************************************/

	//获取redis value (String)  根据key查询
	public String getStr(String key) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		String result;
		try {
			jedis = getJedis();
			result = jedis.get(key);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
		return result;
	}

	/**
	 * 添加key value 单个key value
	 */
	public void setStr(String key, String value) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.set(key, value);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
	}

	/**
	 * 删除key 单个key
	 */
	public void delStr(String  key) throws Exception {
		Jedis jedis = null;
		boolean isBroken = false;
		try {
			jedis = getJedis();
			jedis.del(key);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
	}

	/**
	 * 对key里面的值+1，如果key不存在，则redis中设置为1.
	 * @param key
	 * @return redis 中的新value
	 */
	public long setIncr(String key) throws Exception{
		Jedis jedis = null;
		boolean isBroken = false;
		long value=0;
		try {
			jedis = getJedis();
			value=jedis.incr(key);
		} catch (Exception e) {
			isBroken = true;
			throw e;
		} finally {
			returnResource(jedis, isBroken);
		}
		return value;
	}








}
