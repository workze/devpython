from websocket import WebsocketApp
from util import *


def send(para):
    print para
    clientid = para.get('clientid')
    mes = para.get('message')
    print 'WebsocketApp.send_message_to_client', clientid, mes
    WebsocketApp.send_message_to_client(clientid, mes)
    return build_ok_response(message='ok')


def list_client(para):
    print para
    return WebsocketApp.client_map.keys()



