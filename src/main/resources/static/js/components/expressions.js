const e = React.createElement;

const expressionsBar = () => {
  const [references, setReferences] = React.useState(null);

  React.useEffect(() => {
    fetch("http://localhost:8080/freemarkerReferences")
      .then((responce) => responce.json())
      .then((refs) => setReferences(refs));
  }, []);

  const renderAccordion = (
    name,
    options,
    categories,
    type,
    typeWithoutSpase
  ) => {
    return (
      <div className="card">
        <div
          className={`card-header toolbox--${name}`}
          id={`heading${typeWithoutSpase}`}
        >
          <h2 className="mb-0">
            <button
              className={`button collapsed ${typeWithoutSpase}`}
              type="button"
              data-toggle="collapse"
              data-target={`#${typeWithoutSpase}`}
              aria-expanded={false}
              aria-controls={`${typeWithoutSpase}`}
            >
              {type}
            </button>
          </h2>
        </div>

        <div
          id={`${typeWithoutSpase}`}
          className="collapse"
          aria-labelledby={`heading${typeWithoutSpase}`}
          data-parent={`#${name}`}
        >
          <div className={`card-body toolbox--${name}__menu`}>
            <ul className="nav flex-column">
              {renderOptions(options, categories)}
            </ul>
          </div>
        </div>
      </div>
    );
  };

  const renderOptions = (options, categories) => {
    if (!categories.length) {
      return options.map((item) => (
        <li className="nav-item">
          <button type="button" onClick={() => openModalWithReference(item)}>
            {item}
          </button>
        </li>
      ));
    } else {
      return categories.map((type) => {
        const options = Object.values(references)
          .filter((v) => v.category === type)
          .map((item) => item.name);
        const typeWithoutSpase = type.replace(/ /g, "");
        return (
          <div class="accordion" id="categories">
            {renderAccordion("categories", options, [], type, typeWithoutSpase)}
          </div>
        );
      });
    }
  };

  const expressionTypes = (object) => {
    if (object) {
      const types = [...new Set(Object.values(object).map((v) => v.type))];

      return types.map((type) => {
        const categories = [
          ...new Set(
            Object.values(object)
              .filter((v) => v.type === type)
              .map((v) => v.category)
              .filter(Boolean)
          ),
        ];

        let options = [];
        if (!categories.length) {
          options = [
            ...new Set(
              Object.values(object)
                .filter((v) => v.type === type)
                .map((v) => v.name)
            ),
          ];
        }

        const typeWithoutSpase = type.replace(/ /g, "");
        return renderAccordion(
          "expressionsTypes",
          options,
          categories,
          type,
          typeWithoutSpase
        );
      });
    }
  };

  return (
    <div>
      <div id="expression-container"></div>
      <h3 className="p-3 toolbox--header">Expressions:</h3>
      <div class="accordion" id="expressionsTypes">
        {expressionTypes(references)}
      </div>
    </div>
  );
};

const domContainer = document.querySelector("#expression-container");
ReactDOM.render(e(expressionsBar), domContainer);
