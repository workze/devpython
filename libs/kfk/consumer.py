from kafka import KafkaConsumer
from util import *

health_pool = {}  # clientid:-1 0 1
consumer_pool = {}

def connect(para):
    print para
    logger.info('current pool is %s', len(consumer_pool.keys()))
    clientid = para.get('k.c.clientid')
    server = para.get('k.c.broker')
    topic = para.get('k.c.topic')
    group = para.get('k.c.group')
    if consumer_pool.has_key(clientid):
        logger.info('consumer already in pool, %s' % clientid)
        return build_ok_response(message='connected...')
    try:
        consumer = KafkaConsumer(topic, group_id=group, bootstrap_servers=server)
        health_pool[clientid] = True
        consumer_pool[clientid] = consumer
    except Exception as e:
        return build_exception_response(e)

    logger.info('create consumer success. %s' % clientid)
    return build_ok_response(message='connected')


def feed(para):
    print para
    clientid = para.get('k.c.clientid')


def close(para):
    print para
    clientid = para.get('k.c.clientid')
    if not consumer_pool.has_key(clientid):
        logger.info('consumer already closed')
        return build_ok_response(message='closed...')
    consumer = consumer_pool.get(clientid)
    try:
        consumer.close()
        health_pool.pop(clientid)
        consumer_pool.pop(clientid)
    except Exception as e:
        logger.error(e)
    return build_ok_response(message='closed')


def query(para):
    print para
    clientid = para.get('k.c.clientid')
    if not consumer_pool.has_key(clientid):
        return build_ok_response(data='')
    consumer = consumer_pool.get(clientid)
    records = consumer.poll(1000)
    print 'str(records)',str(records)
    if records != {} and records is not None:
        # records.values()[0][0] ConsumerRecord(topic=u'test', partition=0, offset=109, timestamp=1538028085357L, timestamp_type=0, key=None, value='hello world', checksum=785015740, serialized_key_size=-1, serialized_value_size=11)
        print 'get record', records
        # logger.info(records)
        # res = extract_records_to_map(records)
        return build_ok_response(data=str(records))
    # if not records:
    #     messages = records.values()[0]
    #     return build_ok_response(data=messages)
    # records = consumer.next()
    # for records in consumer:

    return build_ok_response(data='')


def test():
    consumer = KafkaConsumer('', group_id='', bootstrap_servers='')
    consumer.poll(1000, 10)
    consumer.next()


def extract_records_to_map(records):
    values = records.values()
    reses = []
    for consumer_record in values:
        timestamp = consumer_record[0][3]
        value = consumer_record[0][5]
        key = consumer_record[0][4]
        res = {'timestamp': timestamp, 'value': str(value), 'key': str(key)}
        reses.append(res)
    print reses
    return reses
