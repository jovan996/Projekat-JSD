from __future__ import unicode_literals
import os
from os.path import dirname, join
from textx import metamodel_from_file
from textx.export import metamodel_export, model_export


this_folder = dirname(__file__)


class SimpleType(object):
    
    def __init__(self, parent, name):
        self.parent = parent
        self.name = name

    def __str__(self):
        return self.name


def get_metamodel(debug=False):
    
    type_builtins = {
            'integer': SimpleType(None, 'integer'),
            'string': SimpleType(None, 'string')
    }
    entity_mm = metamodel_from_file(join(this_folder, 'term.tx'),
                                    classes=[SimpleType],
                                    builtins=type_builtins,
                                    debug=debug)

    return entity_mm


def main(debug=False):

    term_mm = get_metamodel(debug)

    person_model = term_mm.model_from_file(join(this_folder, 'model.term'))


if __name__ == "__main__":
    main()