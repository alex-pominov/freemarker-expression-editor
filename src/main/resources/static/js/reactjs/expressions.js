const e = React.createElement;

const expressionsBar = () => {
  const [references, setReferences] = React.useState(null);
  console.log(references);

  // Fetch references from server
  React.useEffect(() => {
    fetch("http://localhost:8080/freemarkerReferences")
      .then((responce) => responce.json())
      .then((refs) => setReferences(refs.flat()));
  }, []);

  const onModalOpenHandler = (itemName) => {
    const obj = {...references.filter((item) => item.name === itemName)[0]};
    return openModalWithReference(obj);
  };

  const expressions = (object) => {
    if (object) {
      // Unique types of expressions
      const types = [...new Set(Object.values(object).map((v) => v.type))];

      // Render accordion for each of type
      return types.map((type) => {
        const subcategories = [
          ...new Set(
            Object.values(object)
              .filter((v) => v.type === type)
              .map((v) => v.subcategory)
              .filter(Boolean)
          ),
        ];

        let options = [
          ...new Set(
            Object.values(object)
              .filter((v) => v.type === type && !v.subcategory)
              .map((v) => v.name)
          ),
        ];


        // Create new variable with type without space
        // to avoid naming error during render accodrion
        const typeWithoutSpase = type.replace(/ /g, "");

        return renderAccordion(
          "expressionsTypes",
          options,
          subcategories,
          type,
          typeWithoutSpase
        );
      });
    }
  };

  const renderAccordion = (
    dataParent,
    options,
    categories,
    type,
    typeWithoutSpase
  ) => (
    <div className="card">
      <div
        className={`card-header expressions--${dataParent}`}
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
          {dataParent === "categories" && <i class="arrow"></i>}
        </h2>
      </div>

      <div
        id={`${typeWithoutSpase}`}
        className="collapse"
        aria-labelledby={`heading${typeWithoutSpase}`}
        data-parent={`#${dataParent}`}
      >
        <div className={`card-body expressions--${dataParent}__menu`}>
          <ul className="nav flex-column">
            {renderAccordionOptions(options, categories)}
          </ul>
        </div>
      </div>
    </div>
  );

  const renderAccordionOptions = (options, categories) => {
    if (!categories.length) {
      // if no subcategories than render as a list
      return options.map((item) => (
        <li className="nav-item">
          <button type="button" onClick={() => onModalOpenHandler(item)}>
            {item}
          </button>
        </li>
      ));
    } else {
      const opts = options.filter(x => !categories.includes(x));
      console.log(opts)
      // if type has subcategories than render it as accordion
      return (
        <React.Fragment>
          {opts.length !== 0 && opts.map((item) => (
            <li className="nav-item">
              <button type="button" onClick={() => onModalOpenHandler(item)}>
                {item}
              </button>
            </li>
          ))}
          <div class="accordion" id="categories">
            {categories.map((type) =>
              renderAccordion(
                "categories",
                (options = Object.values(references)
                  .filter((v) => v.subcategory === type)
                  .map((item) => item.name)),
                [],
                type,
                type.replace(/ /g, "")
              )
            )}
          </div>
        </React.Fragment>
      );
    }
  };

  return (
    <React.Fragment>
      <h3 className="expressions--header">Expressions</h3>
      <div class="accordion" id="expressionsTypes">
        {expressions(references)}
      </div>
    </React.Fragment>
  );
};

const domContainer = document.querySelector("#expression-container");
ReactDOM.render(e(expressionsBar), domContainer);
