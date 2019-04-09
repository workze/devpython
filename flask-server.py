from flask import Flask
from flask import jsonify
import mdmanager
import thread
import logging
from flask import request, make_response
import importlib
from config import *
from websocket import WebsocketApp

app = Flask(__name__)
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(name)s - %(levelname)s - %(message)s')
# logging.basicConfig(level = logging.INFO,format = '%(asctime)s - %(name)s - %(levelname)s - %(message)s',filename='app.log')
logger = logging.getLogger('main')


@app.route('/')
def hello_world():
    return build_response('it works')


@app.route('/<module>/<func>', methods=['OPTIONS'])
def call0(module, func):
    return build_response('')


@app.route('/<module>/<func>', methods=['GET', 'POST'])
def call(module, func):
    try:
        para = request.get_json()
        logger.info('call %s/%s, %s' % (module, func, para))
        m = importlib.import_module('.' + module, 'libs')
        f = getattr(m, func)
        call_result = f(para)
        print('call_result is', call_result)
        return build_response(call_result)
    except Exception as e:
        print('Exception is', e)
        logger.error(e)
        return build_response(str(e))


# object to response
def build_response(obj):
    logger.info(obj)
    response = make_response(jsonify(obj))
    response.headers['Access-Control-Allow-Origin'] = '*'
    response.headers['Access-Control-Allow-Methods'] = 'POST,GET'
    response.headers['Access-Control-Allow-Headers'] = 'origin,content-type,accept'
    response.headers['Access-Control-MAX-AGE'] = '1728000'
    return response


if __name__ == '__main__':
    # websocket thread
    thread.start_new_thread(WebsocketApp.start_wsserver, ())

    # watch module changes thread
    thread.start_new_thread(mdmanager.watch, ('./libs',))

    # app.run()
    app.run(host=SERVER_IP)
