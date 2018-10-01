import os, time
import importlib
import logging

logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(name)s - %(levelname)s - %(message)s')
# logging.basicConfig(level = logging.INFO,format = '%(asctime)s - %(name)s - %(levelname)s - %(message)s',filename='app.log')
logger = logging.getLogger('module-manager')
file_time_map = {}
init_time = time.time()

# ./libs/sublib/test.py
def reload_module(f):
    try:
        f = f.replace('./libs/', '').rstrip('.py')  # sublib/test
        module = f.replace('/', '.')
        m = importlib.import_module('.' + module, 'libs')
        reload(m)
        logger.info('reload %s' % module)
    except Exception as e:
        logger.info('reload err, module file is %s, %s' % (f, e))


def watch(path):
    while True:
        g = os.walk(path)
        for p, dir_list, file_list in g:
            for file_name in file_list:
                if file_name.endswith('.py') and file_name != '__init__.py':
                    f = p + '/' + file_name
                    recorded_time = file_time_map.get(f, init_time)
                    last_modify_time = int(os.stat(f).st_mtime)
                    if recorded_time < last_modify_time:
                        file_time_map[f] = last_modify_time
                        reload_module(f)
        time.sleep(5)


if __name__ == '__main__':
    print ''
    # watch('./libs')

