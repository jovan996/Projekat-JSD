from os import mkdir,makedirs
from os.path import exists, dirname, join
import jinja2
from app import get_metamodel
from app import this_folder

APP_DIRECTORY = 'Spring_boot_app\\src\\main\\java\\project'

this_fold = dirname(__file__)
print(this_fold)

def to_pascalcase(st):
    return st[0].upper() + st[1:]


def to_lowercase(st):
    return st[0].lower() + st[1:]

def main():

    mm = get_metamodel()

    model = mm.model_from_file('model/model.term')

    def javatype(s):
        """
        Maps type names from PrimitiveType to Java.
        """
        return {
                'int': 'int',
                'string': 'String',
                'long': 'Long',
                'boolean': 'Boolean'
        }.get(s.name, s.name)

    # Create output folder
    #srcgen_folder = join(this_folder, APP_DIRECTORY)
    #print(srcgen_folder)
    #if not exists(srcgen_folder):
       # makedirs(srcgen_folder)
    main_class_folder = join(this_folder, APP_DIRECTORY)
    print(main_class_folder)
    if not exists(main_class_folder):
        makedirs(main_class_folder)
    model_folder=join(this_folder,APP_DIRECTORY + "\\model")
    if not exists(model_folder):
        makedirs(model_folder)
    repository_folder = join(this_folder, APP_DIRECTORY + "\\repository")
    if not exists(repository_folder):
        makedirs(repository_folder)
    service_folder = join(this_folder, APP_DIRECTORY + "\\service")
    if not exists(service_folder):
        makedirs(service_folder)
    controller_folder = join(this_folder, APP_DIRECTORY + "\\controller")
    if not exists(controller_folder):
        makedirs(controller_folder)

    # Initialize template engine.
    jinja_env = jinja2.Environment(
        loader=jinja2.FileSystemLoader(this_folder),
        trim_blocks=True,
        lstrip_blocks=True)

    # Register filter for mapping Entity type names to Java type names.
    jinja_env.filters['javatype'] = javatype
    jinja_env.filters['to_pascalcase'] = to_pascalcase
    jinja_env.filters['to_lowercase'] = to_lowercase

    # Load Java template
    main_class_template = jinja_env.get_template('template/main_class.template')
    model_template = jinja_env.get_template('template/model.template')
    repository_template = jinja_env.get_template('template/repository.template')
    service_template = jinja_env.get_template('template/service.template')
    controller_template = jinja_env.get_template('template/controller.template')

    for term in model.terms:
        # For each entity generate java file
        #with open(join(srcgen_folder,
                       #"%s.java" % term.name.capitalize()), 'w') as f:
           # f.write(model_template.render(term=term))
        with open(join(main_class_folder, "Application.java"), 'w') as file:
            file.write(main_class_template.render())
        with open(join(model_folder, "%s.java" % term.name), 'w') as file:
            file.write(model_template.render(term=term))
        with open(join(repository_folder, "%sRepository.java" % term.name), 'w') as file:
            file.write(repository_template.render(term=term))
        with open(join(service_folder, "%sService.java" % term.name), 'w') as file:
            file.write(service_template.render(term=term))
        with open(join(controller_folder, "%sController.java" % term.name), 'w') as file:
            file.write(controller_template.render(term=term))


if __name__ == "__main__":
    main()