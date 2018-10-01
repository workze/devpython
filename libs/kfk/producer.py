from util import *
from kafka import KafkaProducer


def send(para):
    server = para.get('k.p.broker')
    topic = para.get('k.p.topic')
    mes = para.get('k.p.message')
    (result, res) = check_param(server, topic, mes)
    if result == False:
        return res

    try:
        producer = KafkaProducer(bootstrap_servers=server, request_timeout_ms=5000)
        producer.send(topic, b'%s' % str(mes)).get(1000)
    except Exception as e:
        logger.error(e)
        return build_err_response(message='send err, %s, %s' % (type(e), e.message))
    return build_response(message='ok')


def check_param(server, topic, mes):
    if not_empty(server):
        return False, build_response(message='invalid broker')
    if not_empty(topic):
        return False, build_response(message='invalid topic')
    if not_empty(mes):
        return False, build_response(message='invalid message')
    return True, ''
