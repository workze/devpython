import logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(name)s - %(levelname)s - %(message)s')
# logging.basicConfig(level = logging.INFO,format = '%(asctime)s - %(name)s - %(levelname)s - %(message)s',filename='app.log')
logger = logging.getLogger('logutil')
file_time_map = {}


def build_response(code=0, message='', data=''):
    res = {}
    res['code'] = code
    res['message'] = message
    res['data'] = data
    return res


def build_ok_response(message='', data=''):
    return build_response(0, message=message, data=data)


def build_err_response(message='', data=''):
    return build_response(400, message=message, data=data)

def build_exception_response(e):
    logger.error(e)
    return build_err_response(message='err, %s, %s' % (type(e), e.message))

def not_empty(s):
    if s:
        return False
    if 'None' == s:
        return False
    return True
